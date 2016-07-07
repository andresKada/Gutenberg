function imprSelec(muestra) {
    var ficha = document.getElementById(muestra);
    var ventimp = window.open(' ', 'popimpr');
    ventimp.document.write(ficha.innerHTML);
    ventimp.document.close();
    ventimp.print();
    ventimp.close();
}


/*function genPDF(){
			html2canvas(document.getElementById("testDiv"), {
				onrendered: function (canvas) {
					var img = canvas.toDataURL("image/png");
					var doc = new jsPDF();
					doc.addImage(img, 'PNG', 0,20);
					doc.save('test.pdf');
				}
			});
		}

document.getElementById("btnPrint").onclick = function () {
	printElement(document.getElementById("printThis"));

	var modThis = document.querySelector("#printSection .modifyMe");
	modThis.appendChild(document.createTextNode(" new"));

	window.print();
}

function printElement(elem) {
	var domClone = elem.cloneNode(true);

	var $printSection = document.getElementById("printSection");

	if (!$printSection) {
			var $printSection = document.createElement("div");
			$printSection.id = "printSection";
			document.body.appendChild($printSection);
	}

	$printSection.innerHTML = "";

	$printSection.appendChild(domClone);
}*/
