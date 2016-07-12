function imprSelec(muestra) {
    /*var ficha = document.getElementById(muestra);
    var ventimp = window.open(' ', 'popimpr');
    ventimp.document.write(ficha.innerHTML);
    ventimp.document.close();
    ventimp.print();
    ventimp.close();*/
    var ficha = document.getElementById(muestra);
    var ventimp = window.open(' ', 'popimpr');
    ventimp.document.write('<html><head><title></title><link rel="stylesheet" href="../../content/css/bootstrap.min.css"><link rel="stylesheet" href="../../content/css/estilos_gu.css"><link rel="stylesheet" href="../content/css/font-awesome.css"></head><body>');
    //ventimp.document.write('<div class="container"><div class="row"><div class="col-lg-8 col-lg-offset-2  col-sm-8 col-sm-offset-2 gu">');
    //ventimp.document.write('<div class="col-lg-8 col-lg-offset-2 col-sm-8 col-sm-offset-2 gu"><img src="../content/images/gutenberg_logo-06.png" class="image-responsive center-block"><img src="../content/images/logo-llevar-leyendo-alfa.png" class="image-responsive logo_feria center-block">');
    ventimp.document.write(ficha.innerHTML);
    //entimp.document.write('<div class=""><div class="col-lg-6 col-sm-6 top"><img src="../content/images/logo-letra-capital-alfa.png" class="image-responsive logo_foot img_margin "></div><div class="col-lg-6 col-sm-6 top"><img src="../content/images/logo-abarrote-de-diseño-alfa.png" class="image-responsive logo_foot"> </div></div><div class=""><div class="col-lg-6 col-sm-6"><img src="../content/images/logo-BPC-alfa.png" class="image-responsive logo_foot_2 img_margin"></div><div class="col-lg-6 col-sm-6"><img src="../content/images/logo-kada-alfa.png" class="image-responsive logo_foot_kada "></div></div></div></div>');
    ventimp.document.write('</body></html>');
    //ventimp.document.close();
    ventimp.print();

}
