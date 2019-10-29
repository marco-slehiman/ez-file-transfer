<jsp:root 
    xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fn="http://java.sun.com/jsp/jstl/functions"
    xmlns="http://www.w3.org/1999/xhtml"
    version="2.0">
   "use strict";

   var BUFF_SIZE = 4194304;
   var fileTransferSocket  = 0;
   var chunks = 0;
   var currentChunk = 0;
   function initFileTransferSocket() {
      if ('WebSocket' in window) {
<c:choose>
   <c:when test="${fn:contains(pageContext.request.scheme, 's')}">
      <c:choose>
         <c:when test="${fn:contains(pageContext.request.localName, ':')}">
         var ws_url = 'wss://[${pageContext.request.localName}]:${pageContext.request.localPort}${pageContext.servletContext.contextPath}/websocket/fileTransfer';
         </c:when>
         <c:otherwise>
         var ws_url = 'wss://${pageContext.request.localName}:${pageContext.request.localPort}${pageContext.servletContext.contextPath}/websocket/fileTransfer';
         </c:otherwise>
      </c:choose>
   </c:when>
   <c:otherwise>
      <c:choose>
         <c:when test="${fn:contains(pageContext.request.localName, ':')}">
         var ws_url = 'ws://[${pageContext.request.localName}]:${pageContext.request.localPort}${pageContext.servletContext.contextPath}/websocket/fileTransfer';
         </c:when>
         <c:otherwise>
         var ws_url = 'ws://${pageContext.request.localName}:${pageContext.request.localPort}${pageContext.servletContext.contextPath}/websocket/fileTransfer';
         </c:otherwise>
      </c:choose>
   </c:otherwise>
</c:choose>
      fileTransferSocket = new WebSocket(ws_url);
      fileTransferSocket.onopen = function(s){
         console.log('Socket Has been opened');
      };
      fileTransferSocket.onerror = function(error){
         console.log('ws error: '+error)
      };
      
      fileTransferSocket.onmessage = function(evt){
         console.log('received msg: ' + evt.data);
         var progressBar = document.getElementById('progressBar')
         progressBar.style.width = evt.data + '%';
         var progressText = document.getElementById('progressText')
         progressText.innerHTML = evt.data + '%';
         if(evt.data == '100'){
            var progressBar = document.getElementById('progressBar')
            progressBar.style.width = '0%';
            var progressBarDiv = document.getElementById('progressBar')
            progressBarDiv.style.visibility = 'hidden';
            $('#fileTransferButton').prop('disabled', false);
         }
         
      };

      fileTransferSocket.onclose = function(){
         console.log('close');
      };

      } else {
         alert('This browser does not support websockets');
      }
   }
   
   initFileTransferSocket();

   function transferFile() {
      if (!isValidFormData()){
          return;
      }
      $('#fileTransferButton').prop('disabled', true);
      var progressBarDiv = document.getElementById('progressBar')
      progressBarDiv.style.visibility = 'visible';
      var file = document.getElementById('files')
      var f =file.files[0];
      var msg = new Object();   
      msg.op = 'put';
      msg.fname = f.name;
      msg.fsize = f.size;
      msg.emailfrom = $("#emailFrom").val();
      msg.emailto = $("#emailTo").val();
      
      //Sending File Name and File Size to Websocket ()
      fileTransferSocket.send(JSON.stringify(msg));
      //console.log('send test: '+JSON.stringify(msg));
      chunks = Math.ceil(f.size / BUFF_SIZE);
      console.log('Chunks' + chunks);
      for ( var j = 0; j &lt; chunks; j++) {
         console.log('Chunk #' + j);
         var reader = new FileReader();
         reader.onload = function(evt) {
            var data = evt.target.result.slice(0);
            fileTransferSocket.binaryType = "blob";
            fileTransferSocket.send(data);
            currentChunk++;
            
         };

         var upper = BUFF_SIZE * (j + 1);
         if (upper > f.size) {
            upper = f.size;
         }
         var slice = f.slice(BUFF_SIZE * j, upper);
         var sequence = new Blob([zeroPad(j,3) + "|"], { type: slice.type});
         var blob = new Blob([sequence, slice], {type: slice.type});
         reader.readAsArrayBuffer(blob);
      }
   }

   function errorHandler(evt) {
      switch (evt.target.error.code) {
         case evt.target.error.NOT_FOUND_ERR:
            alert('File Not Found!');
            break;
         case evt.target.error.NOT_READABLE_ERR:
            alert('File is not readable');
            break;
         case evt.target.error.ABORT_ERR:
            break; // noop
         default:
            alert('An error occurred reading this file.');
       }
    }

   function zeroPad(num, places) {
      var zero = places - num.toString().length + 1;
      return Array(+(zero > 0 &#38;&#38; zero)).join("0") + num;
   }
 
   function validateEmail(email) {
      var re = /^(([^&lt;&gt;()[\]\\.,;:\s@\&#34;]+(\.[^&lt;&gt;()[\]\\.,;:\s@\&#34;]+)*)|(\&#34;.+\&#34;))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
   }

   function isValidFormData() {
      $("#result").text("");
      var emailFrom = $("#emailFrom").val();
      var emailTo = $("#emailTo").val();
      var hasError = false;
      var fileTransferMessagesText ="";
      if (emailFrom.length==0) {
         fileTransferMessagesText += "Tu cuenta de correo es requerida :(&lt;br /&gt;";
         $('#emailFrom').parent().removeClass("has-success");
         $('#emailFrom').parent().addClass("has-error");
         hasError = true;
      } else if (!validateEmail(emailFrom)) {
         fileTransferMessagesText += "Tu cuenta de correo no es valida :(&lt;br /&gt;";
         $('#emailFrom').parent().removeClass("has-success");
         $('#emailFrom').parent().addClass("has-error");
         hasError = true;
      } else{
         $('#emailFrom').parent().removeClass("has-error");
         $('#emailFrom').parent().addClass("has-success");
      } 
      
      if (emailTo.length==0) {
         fileTransferMessagesText += "La cuenta de correo de quien recibira el archivo es requerida :(&lt;br /&gt;";
         $('#emailTo').parent().removeClass("has-success");
         $('#emailTo').parent().addClass("has-error");
         hasError = true;
      } else if (!validateEmail(emailTo)) {
         fileTransferMessagesText += "La cuenta de correo de quien recibira el archivo no es valida :(&lt;br /&gt;";
         $('#emailTo').parent().removeClass("has-success");
         $('#emailTo').parent().addClass("has-error");
         hasError = true;
      } else {
         $('#emailTo').parent().removeClass("has-error");
         $('#emailTo').parent().addClass("has-success");
      }
      if($.trim($('#files').val()).length == 0){
         fileTransferMessagesText += "Por favor selecciona un archivo / documento :(&lt;br /&gt;";
         $('#files').parent().removeClass("has-success");
         $('#files').parent().addClass("has-error");
         hasError = true;
      }else{
         $('#files').parent().removeClass("has-error");
         $('#files').parent().addClass("has-success");
      }
      
      if(hasError){
         $("#fileTransferMessages").html( fileTransferMessagesText);
         $("#fileTransferMessages").css("color", "red");
      }else {
         $("#fileTransferMessages").html("El archivo se esta cargando :)");
         $("#fileTransferMessages").css("color", "green");
      }
      return !hasError;
    }

</jsp:root>   