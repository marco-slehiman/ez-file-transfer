			function handleFileSelect(evt) {
				// Reset progress indicator on new file selection.
				progress.style.width = '0%';
				progress.textContent = '0%';

				reader = new FileReader();
				reader.onerror = errorHandler;
				reader.onprogress = updateProgress;
				reader.onabort = function(e) {
					alert('File read cancelled');
				};
				reader.onloadstart = function(e) {
					document.getElementById('progress_bar').className = 'loading';
				};
				reader.onload = function(e) {
					// Ensure that the progress bar displays 100% at the end.
					progress.style.width = '100%';
					progress.textContent = '100%';
					setTimeout(
							"document.getElementById('progress_bar').className='';",
							2000);
				}

				// Read in the image file as a binary string.
				reader.readAsBinaryString(evt.target.files[0]);
			}
