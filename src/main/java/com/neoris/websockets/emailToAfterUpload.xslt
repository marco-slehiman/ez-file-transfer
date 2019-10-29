<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:PLZ="http://www.sat.gob.mx/esquemas/ContabilidadE/1_1/PolizasPeriodo"
	xmlns:fn="http://www.w3.org/2005/xpath-functions" 
	xmlns:java="java"
	exclude-result-prefixes="PLZ xls fn java" >
	<xsl:output method="html" version="5.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"  />
	<xsl:strip-space elements="*"/>
	<xsl:template match="/">
	<xsl:variable name="currentDate" select="java:util.Date.new()"></xsl:variable>
	<xsl:variable name="processTime" select="ezFileTransfer/process-date"></xsl:variable>
	<xsl:variable name="downloadURL" select="ezFileTransfer/download-url"></xsl:variable>
<html lang="en">
  <head>
    <title>Neoris ez File Transfer</title>
  </head>
  <body>
<!-- Empieza Contenido -->

  <h2><xsl:value-of select="ezFileTransfer/emailFrom" />  te ha enviado varios archivos.</h2>
  <h3>Mensaje</h3>
  <p><xsl:value-of select="ezFileTransfer/message" /></p>
  <xsl:value-of select="$downloadURL"></xsl:value-of>
  <xsl:value-of select="ezFileTransfer/date-formated"></xsl:value-of>
  <xsl:value-of select="$currentDate"></xsl:value-of>
<table>
<tr>
<td> RFC Emisor</td>
<td> UUID </td>
<td> Moneda</td>
<td> Tipo de Cambio </td>
<td> Monto Total </td>
</tr>

<xsl:for-each select="ezFileTransfer/files/file">
        <tr>
            <td> <xsl:value-of select="text()" /> </td>
        </tr>
</xsl:for-each>
</table>
<!-- Termina Contenido -->
 </body>
  
  
  
  
</html>



</xsl:template>
<xsl:template name="detalle">
    <xsl:param name="numeroPoliza" />
    <xsl:param name="fecha" />
    <xsl:param name="conceptoPoliza" />
    <xsl:param name="nodos" />
	<xsl:for-each select="$nodos/PLZ:Transaccion">
        <tr>
            <td> <xsl:value-of select="$numeroPoliza" /> </td>
            <td> <xsl:value-of select="$fecha" /> </td>
            <td> <xsl:value-of select="$conceptoPoliza" /> </td>
            <td> <xsl:value-of select="@NumCta" /> </td>
            <td> <xsl:value-of select="@DesCta" /> </td>
            <td> <xsl:value-of select="@Concepto" /> </td>
            <td> <xsl:value-of select="@Debe" /> </td>
            <td> <xsl:value-of select="@Haber" /> </td>
        </tr>

    </xsl:for-each>

</xsl:template>

</xsl:stylesheet> 
