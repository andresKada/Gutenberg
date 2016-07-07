function genPDF(){
			html2canvas(document.getElementById("testDiv"), {
				onrendered: function (canvas) {
					var img = canvas.toDataURL("image/png");
					var doc = new jsPDF();
					doc.addImage(img, 'PNG', 0,20);
					doc.save('test.pdf');
				}
			});
		}
