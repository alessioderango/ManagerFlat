<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<link rel="shortcut icon" href="resource/img/service-manager-icon.png"
	type="image/png">
<title>ManagerApp</title>
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="resource/css/bootstrap.css" rel="stylesheet" />
<link href="resource/css/my.css" rel="stylesheet" />
<!-- FONTAWESOME STYLE CSS -->
<link href="resource/css/font-awesome.min.css" rel="stylesheet" />
<!-- CUSTOM STYLE CSS -->
<link href="resource/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link href="resource/css/jquery.alerts.css" rel="stylesheet"
	type="text/css" media="screen">
<link rel="stylesheet" type="text/css" media="screen"
	href="resource/css/bootstrap-datetimepicker.css">

</head>

<body>
	<!-- Logo & Navigation starts -->
	<jsp:include page="include/header.jsp" />
	<!-- Logo & Navigation end -->

	<!--/.NAVBAR END-->

	<section id="intro">
		<div class="container">
			<div class="row text-center">
				<div class="col-md-16">
					<div class="row text-center pad-row  ">
						<div class="col-md-4 col-sm-4 ">
							<div class="container">
								<h2>
									Benvenuto
									<c:out value="${admin.name}" />
								</h2>

							</div>
						</div>

					</div>

				</div>

			</div>
		</div>
	</section>
	<section id="manager">
		<div class="container">
			<div class="row text-center">

				<div class="col-md-12">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading" style="text-align: center;">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne"> Inserisci Nuova Lettura </a>
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<br>
								<div class="col-md-9 col-md-offset-2">
									<div class="col-sm-13">
										<c:set var="theString" value="${resultInserimento}" />
										<c:choose>
											<c:when test="${fn:contains(theString, 'errore')}">
												<div class="text-error" id="error">
													<strong><c:out value="${resultInserimento}" /></strong>
												</div>
											</c:when>
											<c:otherwise>
												<div class="text-success" id="success">
													<strong><c:out value="${resultInserimento}" /></strong>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
									<form class="form-horizontal" role="form"
										action="inserisciLettura.html">

										<label class="col-sm-offset-2 col-sm-2 control-label">Stanza</label>

										<div class="form-group ">
											<select class="span2 col-sm-offset-1 col-sm-2" name="Stanza" id="selectStanza">
												<c:choose>
													<c:when test="${stanza == '1'}">
														<option selected>1</option>
													</c:when>
													<c:otherwise>
														<option>1</option>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${stanza == '2'}">
														<option selected>2</option>
													</c:when>
													<c:otherwise>
														<option>2</option>
													</c:otherwise>
												</c:choose>

												<c:choose>
													<c:when test="${stanza == '3'}">
														<option selected>3</option>
													</c:when>
													<c:otherwise>
														<option>3</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${stanza == '4'}">
														<option selected>4</option>
													</c:when>
													<c:otherwise>
														<option>4</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${stanza == 'comune'}">
														<option selected>comune</option>
													</c:when>
													<c:otherwise>
														<option>comune</option>
													</c:otherwise>
												</c:choose>
											</select>

										</div>
										<div class="form-group ">
											<label class="col-sm-offset-2 col-sm-2 control-label">Luce</label>
											<div class="col-sm-8">
												<input class="form-control fixLarghezza" name="Luce"
													placeholder="Luce" value="${luce}">
											</div>
											<c:if test="${not empty luceValueErrato}">
												<div class="text-error" id="error">
													<strong><c:out value="${luceValueErrato}" /></strong>
												</div>
											</c:if>
										</div>
										<div class="form-group">
											<label class="col-sm-offset-2 col-sm-2 control-label">
												Acqua Fredda</label>
											<div class="col-sm-8">
												<input class="form-control fixLarghezza" name="AcquaFredda"
													placeholder="Acqua Fredda" value="${acquaFredda}">
											</div>
											<c:if test="${not empty acquaFreddaValueErrato}">
												<div class="text-error" id="error">
													<strong><c:out value="${acquaFreddaValueErrato}" /></strong>
												</div>
											</c:if>
										</div>
										<div class="form-group">
											<label class="col-sm-offset-2 col-sm-2 control-label">
												Acqua Calda</label>
											<div class="col-sm-8">
												<input class="form-control fixLarghezza" name="AcquaCalda"
													placeholder="Acqua Calda" value="${acquaCalda}">
											</div>
											<c:if test="${not empty acquaCaldaValueErrato}">
												<div class="text-error" id="error">
													<strong><c:out value="${acquaCaldaValueErrato}" /></strong>
												</div>
											</c:if>

										</div>
										<div class="form-group" id="gas">
											<label class="col-sm-offset-2 col-sm-2 control-label">
												Gas </label>
											<div class="col-sm-8">
												<input class="form-control fixLarghezza" name="Gas"
													placeholder="Gas" value="${gas}">
											</div>
											<c:if test="${not empty gasValueErrato}">
												<div class="text-error" id="error">
													<strong><c:out value="${gasValueErrato}" /></strong>
												</div>
											</c:if>
										</div>
										<c:if test="${not empty dataErrata}">
												<div class="text-error" id="error">
													<strong><c:out value="${dataErrata}" /></strong>
												</div>
											</c:if>
										<div class="form-group">
											<label class="col-sm-offset-2 col-sm-2 control-label">
												Data </label>
											<div class="col-sm-4">
												<div class="form-group ">
													<input type="text" class="span2" id="dp1" name="Data">

												</div>
											</div>

										</div>
										
										<div class="form-group">
											<div class="col-sm-offset-1 col-sm-10">
												<button id="inserisciLettura" type="submit"
													class="btn btn-default">Inserisci</button>
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo" id="modificaEliminaLettura"> Modifica o Elimina Lettura </a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse">
							<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<div class="text-success" id="successEliminazione">
											<strong> Modifica avvenuta con successo</strong>
										</div>
									</div>
									<br>
							</div>
								<div class="panel-body" id="visualizzaTableModificaEliminaLetture"></div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseFour" id="visualizzaLetture"> Visualizza
										Letture </a>
								</h4>
							</div>
							<div id="collapseFour" class="panel-collapse collapse">
								<div class="panel-body" id="visualizzaTableLetture"></div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseFive" id="impostaParametri"> Imposta Parametri </a>
								</h4>
							</div>
							<div id="collapseFive" class="panel-collapse collapse">
							<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<div class="text-success" id="successModificaParametri">
											<strong> Modifica avvenuta con successo</strong>
										</div>
									</div>
									<br>
							</div>
								<div class="col-md-10 col-md-offset-1" id="impostaParametriDiv">
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseSeven" id="visualizzaLettureCheckbox">
										Consumi Pdf </a>
								</h4>
							</div>

							<div id="collapseSeven" class="panel-collapse collapse">
								<!-- 	<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<button id='inserisciLettura' type='submit'
											class='btn btn-default'>Inserisci</button>
									</div>
								</div> -->
								<h4>Seleziona le ultime 5 letture di cui vuoi creare i pdf, il sistema in automatico selezioner&aacute; le
									precedente 5 letture e calcoler&aacute; i consumi</h4>
								<div class="row" id="errorCreaPdfRow">
									<div class='col-sm-offset-1 col-sm-10'>
										<div class="text-error" id="errorCreaPdf">
											<strong></strong>
										</div>
									</div>
								</div>
								<div class="row" id="consumoStanza1">
									<div class='col-sm-offset-1 col-sm-10'>
										<h3>
											<a href="downloadPDFStanza1.html"> PDF Consumi Stanza 1</a>
										</h3>
									</div>
								</div>
								<div class="row" id="consumoStanza2">
									<div class='col-sm-offset-1 col-sm-10'>
										<h3>
											<a href="downloadPDFStanza2.html"> PDF Consumi Stanza 2</a>
										</h3>
									</div>
								</div>
								<div class="row" id="consumoStanza3">
									<div class='col-sm-offset-1 col-sm-10'>
										<h3>
											<a href="downloadPDFStanza3.html"> PDF Consumi Stanza 3</a>
										</h3>
									</div>
								</div>
								<div class="row" id="consumoStanza4">
									<div class='col-sm-offset-1 col-sm-10'>
										<h3>
											<a href="downloadPDFStanza4.html"> PDF Consumi Stanza 4</a>
										</h3>
									</div>
								</div>
								<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<div id="divTableInquiline">
										
										</div>
									</div>
									<br>
								</div>
								<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<button id='inviaEmail' type='submit' class='btn btn-default'>Invia
											Email</button>
									</div>
									<br>
								</div>
								<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<div class="text-success" id="successEmail">
											<strong> Email inviate con successo</strong>
										</div>
									</div>
									<br>
								</div>
								<div class='row'>
									<div class='col-sm-offset-1 col-sm-10'>
										<button id='creaPDF' type='submit' class='btn btn-default'
											onClick='creaPDF();'>Crea PDF</button>
									</div>
								</div>
								<div class="panel-body" id="visualizzaTableLettureCheckbox"></div>
								<!-- <div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<button id='inserisciLettura' type='submit'
											class='btn btn-default'>Inserisci</button>
									</div>
								</div> -->

							</div>

						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseEight" id="GestisciInquilino"> Gestisci Inquilino </a>
								</h4>
							</div>
							<div id="collapseEight" class="panel-collapse collapse">
							<div class="row">
									<div class='col-sm-offset-1 col-sm-10'>
										<div class="text-success" id="successAggiornamento">
											<strong> Modifica avvenuta con successo</strong>
										</div>
									</div>
									<br>
							</div>
								<div class="panel-body" id="visualizzaTableGestioneInquilino"></div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>


	</section>

	<!--/.INTRO END-->



	<section id="footer-sec"></section>
	<!--/.FOOTER END-->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- CORE JQUERY  -->
	<script src="resource/js/prettify.js"></script>
	<script src="resource/js/jquery.js"></script>
	<script src="resource/js/bootstrap-datepicker.js"></script>
	<script src="resource/js/inserisciLettura.js"></script>
	<script src="resource/js/jquery.tablesorter.js"></script>
	<script src="resource/js/jquery.alerts.js" type="text/javascript"></script>
	<!-- BOOTSTRAP SCRIPTS  -->
	<script src="resource/plugins/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS  -->
	<!-- 	<script src="resource/js/custom.js"></script> -->
	<script>
		function creaPDF() {
			var toSend = "";
			var count = 0;
			$('#lettureTableCheckbox tr').each(function(indexR, value) {
				if (indexR != 0) {
					$(this).find('td').each(function(index, value) {
						if (index == 0) {
							if ($(this).children().is(':checked')) {
								toSend += "true,"+$('#lettureTableCheckbox tr').eq(indexR).find('td').eq(2).attr('id')+ ";";
								var data= $('#lettureTableCheckbox tr').eq(indexR).find('td').eq(1).html();
								 $('#lettureTableCheckbox tr').each(function(indexR2, value) {
									$(this).find('td').each(function(index2, value) {
										if (index2 == 0) {
											if (!$(this).children().is(':checked')) {
												var data2=$('#lettureTableCheckbox tr').eq(indexR2).find('td').eq(1).find('div').text();
												if(data === data2){
													toSend += "true,"+$('#lettureTableCheckbox tr').eq(indexR2).find('td').eq(2).attr('id') + ";";
												} 
											}
										}
									});
								});
									if (indexR != 0) {
								}
								toSend += "#";
								count++;
							}
						}
					});
				}
			});
			if (count < 2) {
				jAlert('devi selezionare almeno 2 letture', 'Alert');
				return;
			}
			if (count > 2) {
				jAlert('puoi selezionare solo due date', 'Alert');
				return;
			}

			/* jAlert(toSend, 'Alert'); */
			$.ajax({
				type : "Post",
				url : "creaPDFLetture.html",
				data : "Data=" + toSend,
				success : function(response) {
					if (response === "ok") {

						$('#consumoStanza1').show();
						$('#consumoStanza2').show();
						$('#consumoStanza3').show();
						$('#consumoStanza4').show();
						$('#inviaEmail').show();
						$.ajax({
							type : "get",
							url : 'getTableInquilino.html',
							success : function(data) {
								if(data==="noAdmin"){
									window.location.replace("http://localhost:8080/ManagerFlat/index.html");
								}else{
									var count =0;
									$('#divTableInquiline').append(data);
									$('#inviaEmail').click(function() {
										var toSend = "";
										 $('#tableInquilino tr').each(function(indexR, value) {
												$(this).find('td').each(function(index, value) {
													if (index == 1) {
														if ($(this).children().is(':checked')) {
															toSend += $('#tableInquilino tr').eq(indexR).find('td').eq(0).html()+","+$('#tableInquilino tr').eq(indexR).find('td').eq(0).attr('id') + ";";
															count++;
														}
													}
												});
											});
										 if (count ==0) {
												jAlert('devi selezionare almeno una stanza', 'Alert');
												return;
											}
										 $.ajax({
											type : "get",
											url : 'inviaLetture.html',
											data : "Data=" + toSend,
											success : function(data) {
												if (data === "ok") {
													$('#successEmail').show();
												}
											},
											error: function(jqXHR, exception) {
												window.location.replace("http://localhost:8080/ManagerFlat/index.html");
											}
										}); 
									});
								}
							}
						});
						//$('#tableInquilino').show();
						/*  location.reload(); */
					} else {
						if (response === "error"){				
							$('#errorCreaPdf').show().find('strong').text('Errore nella selezione delle letture o nel numero di letture');
						}
						if (response === "noAdmin"){				
							window.location.replace("http://localhost:8080/ManagerFlat/index.html");
						}
					}

				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}

		$(function() {
			
			
			//al cambiare della select nascondo il gas
			$("select").change(function () {
				if($( "#selectStanza option:selected" ).text()==="comune")
						$('#gas').show();
				else
						$('#gas').hide();
					

			}).trigger('change');
			
			setTimeout(function() {
		          $("#success").hide();
		          $("#error").hide();
		          $("#successEmail").hide();
		          $('#successEliminazione').hide();
		       }, 4000);
			$('#consumoStanza1').hide();
			$('#consumoStanza2').hide();
			$('#consumoStanza3').hide();
			$('#consumoStanza4').hide();
			$('#successAggiornamento').hide();
			$('#inviaEmail').hide();
			/* $('#tableInquilino').hide(); */
			$('#errorCreaPdf').hide();
			$('#successEmail').hide();
			$('#successModificaParametri').hide();
			$('#successEliminazione').hide();

			

			$('#visualizzaLetture').click(function() {
				if (!$("#lettureTable").length) {
					$.ajax({
						url : 'viewTableLetture.html',
						success : function(data) {
							if(data==="noAdmin"){
								window.location.replace("http://localhost:8080/ManagerFlat/index.html");
							}else{
								$('#visualizzaTableLetture').append(data);
								$("#lettureTable").tablesorter({
									sortList : [ [ 0, 1 ] ],
									dateFormat : 'uk',

								});
							}
						},
						error: function(jqXHR, exception) {
							window.location.replace("http://localhost:8080/ManagerFlat/index.html");
						}
					});
					
				}
			});
			$('#GestisciInquilino').click(function() {
				if (!$("#gestioneInquilinoTable").length) {
					$.ajax({
						url : 'viewTableGestioneInquilino.html',
						success : function(data) {
							if(data==="noAdmin"){
								window.location.replace("http://localhost:8080/ManagerFlat/index.html");
							}else{
								$('#visualizzaTableGestioneInquilino').append(data);
								$('.salvaModificaGestioneInquilino').click(function() {
									
									var toSend = "";
									 var id=$(this).attr('id');
									 $('#gestioneInquilinoTable tr').each(function(indexR, value) {
											$(this).find('td').each(function(index, value) {
												if (index == 3) {
													if ($('#gestioneInquilinoTable tr').eq(indexR).find('td').eq(3).find('button').attr('id')===id) {
														var email = $('#gestioneInquilinoTable tr').eq(indexR).find('td').eq(2).find('input').val();
														toSend += "nome="+nome+";email="+email+";"+"id="+id+";";
														alert(toSend)
														$.ajax({
																url : 'aggiornaInquilino.html',
																data : "Data=" + toSend,
																success : function(data) {
																	if(data ==="ok"){
																			$('#successAggiornamento').show();
																			setTimeout(function() {
																			location.reload();
																			$('#successAggiornamento').hide();
																	       }, 2000);
																		
																	}
																},
																error: function(jqXHR, exception) {
																	window.location.replace("http://localhost:8080/ManagerFlat/index.html");
																}
														});
														 return;
													}
												}
											});
										});
									 
									
								});
							}
						},
						error: function(jqXHR, exception) {
							window.location.replace("http://localhost:8080/ManagerFlat/index.html");
						}
					});
				} 
			});
			$('#modificaEliminaLettura').click(function() {
				if (!$("#modificaSalvaTable").length) {
					$.ajax({
						url : 'visualizzaTableEliminaModifica.html',
						success : function(data) {
							
							$('#visualizzaTableModificaEliminaLetture').append(data);
							$("#modificaSalvaTable").tablesorter({
								sortList : [ [ 0, 1 ] ],
								dateFormat : 'uk',

							});
							//salvo modifiche
							$('.salvaModifica').click(function() {
								var toSend = "";
								 var id=$(this).attr('id');
								 $('#modificaSalvaTable tr').each(function(indexR, value) {
										$(this).find('td').each(function(index, value) {
											if (index == 7) {
												if ($('#modificaSalvaTable tr').eq(indexR).find('td').eq(7).find('button').attr('id')===id) {
													var gas = $('#modificaSalvaTable tr').eq(indexR).find('td').eq(5).find('input').val();
													var acquac = $('#modificaSalvaTable tr').eq(indexR).find('td').eq(2).find('input').val();
													var acquaf = $('#modificaSalvaTable tr').eq(indexR).find('td').eq(3).find('input').val();
													var luce = $('#modificaSalvaTable tr').eq(indexR).find('td').eq(4).find('input').val();
													toSend += "id="+id+";gas="+gas+";acquac="+ acquac+";acquaf="+acquaf+";luce="+luce+";";
													$.ajax({
															url : 'modificaLetture.html',
															data : "Data=" + toSend,
															success : function(data) {
																if(data ==="ok"){
																		$('#successEliminazione').show();
																	setTimeout(function() {
																		location.reload();
																		$('#successEliminazione').hide();
																       }, 2000);
																	
																}
															},
															error: function(jqXHR, exception) {
																window.location.replace("http://localhost:8080/ManagerFlat/index.html");
															}
													});
													 return;
												}
											}
										});
									});
								 
								
							});
							$('#eliminaLettura').click(function() {
								
								var toSend = "";
								 $('#modificaSalvaTable tr').each(function(indexR, value) {
										$(this).find('td').each(function(index, value) {
											if (index == 6) {
												if ($(this).children().is(':checked')) {
													toSend += "true,"+$('#modificaSalvaTable tr').eq(indexR).find('td').eq(7).find('button').attr('id') + ";";
													
												}
											}
										});
									});
								 $.ajax({
										url : 'eliminaLetture.html',
										data : "Data=" + toSend,
										success : function(data) {
											if(data ==="ok"){
												$('#successEliminazione').show();
												setTimeout(function() {
													location.reload();
													$('#successEliminazione').hide();
											       }, 2000);
												
											}else{
												if (response === "noAdmin"){				
													window.location.replace("http://localhost:8080/ManagerFlat/index.html");
												}
											}
										},
										error: function(jqXHR, exception) {
											window.location.replace("http://localhost:8080/ManagerFlat/index.html");
										}
								});
							});
						},
						error: function(jqXHR, exception) {
							window.location.replace("http://localhost:8080/ManagerFlat/index.html");
						}
					});
				}
			});
			$('#visualizzaLettureCheckbox').click(function() {
				if (!$("#lettureTableCheckbox").length) {
					$.ajax({
						url : 'viewTableLettureCheckbox.html',
						success : function(data) {
							$('#visualizzaTableLettureCheckbox').append(data);
							
							$("#lettureTableCheckbox").tablesorter({
								
								sortList : [ [ 1, 1 ]],
								sortForce : [ [ 1, 1 ]],
								headers: {0 : { sorter: false },2 : { sorter: false },3 : { sorter: false },4 : { sorter: false },5 : { sorter: false }},
								dateFormat : 'uk',

							});
							$('#lettureTableCheckbox th:eq(1)').addClass('sorter-false')[0].sortDisabled = true;
					    	$('table').trigger('update');
							
						},
						error: function(jqXHR, exception) {
							window.location.replace("http://localhost:8080/ManagerFlat/index.html");
						}
					});
				}
			});

			 

			
			$('#impostaParametri').click(function() {
				
					$.ajax({
						url : 'impostaParametri.html',
						success : function(data) {
							if(data==="noAdmin"){
								//window.location.replace("http://localhost:8080/ManagerFlat/index.html");
							}else{
							if (!$.trim( $('#impostaParametriDiv').html() ).length) {
								$('#impostaParametriDiv').append(data);
								$('#buttonImpostaParametri').click(function(e) {
									e.preventDefault();
									var costoKW = $('#inputCostoKW').val();
									var costoGas = $('#inputCostoGasLitro').val();
									var costoAcqua = $('#inputCostoAcquametrocubo').val();
									var toSend=costoKW+";"+costoGas+";"+costoAcqua+";";
									$.ajax({
										url : 'modificaParametri.html',
										data : "Data=" + toSend,
										type: 'GET',
										success : function(data) {
											
											 if(data==="ok"){
												$('#successModificaParametri').show();
												setTimeout(function() {
													$('#successModificaParametri').hide();
											       }, 2000);
											}
										},
										error: function(jqXHR,textStatus, exception) {
											 alert(JSON.stringify(jqXHR)); 
											 alert("AJAX error: " + textStatus + ' : ' + exception); 
											//window.location.replace("http://localhost:8080/ManagerFlat/index.html");
										}
									
									});
									
								});
							}
						  }
						},
						error: function(jqXHR, exception) {
							//window.location.replace("http://localhost:8080/ManagerFlat/index.html");
						}
					});
				
			});

			$('#dp1').datepicker({
				format : 'dd-mm-yyyy',
				todayBtn : 'linked'
			});
			$("#dp1").datepicker().datepicker("setDate", new Date());
			$('#dp2').datepicker({
				format : 'dd-mm-yyyy',
				todayBtn : 'linked'
			});
			$("#dp2").datepicker().datepicker("setDate", new Date());
			$('#dp3').datepicker({
				format : 'dd-mm-yyyy',
				todayBtn : 'linked'
			});

			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0!
			var yyyy = today.getFullYear();

			if (dd < 10) {
				dd = '0' + dd;
			}

			if (mm < 10) {
				mm = '0' + mm;
			}

			today = dd + '-' + mm + '-' + yyyy;
			$("#dp1").val(today);
			$("#dp2").val(today);
			dd = dd + 1;
			today = dd + '-' + mm + '-' + yyyy;
			$("#dp3").datepicker().datepicker("setDate",
					new Date(yyyy, mm - 1, dd));
			$("#dp3").val(today);

			var startDate = new Date(yyyy, mm - 1, dd);
			var endDate = new Date(yyyy, mm - 1, dd + 1);
			$('#alert').hide();
			$('#dp2').datepicker().on(
					'changeDate',
					function(ev) {
						if (ev.date.valueOf() > endDate.valueOf()) {
							$('#alert').show().find('strong').text(
									'errore sulle date');
							$('#dp2').datepicker('hide');
						} else {
							$('#alert').hide();
							startDate = new Date(ev.date);
							$('#startDate').text($('#dp2').data('date'));
						}
						/* $('#dp2').datepicker('hide'); */
					});
			$('#dp3').datepicker().on(
					'changeDate',
					function(ev) {
						if (ev.date.valueOf() < startDate.valueOf()) {
							$('#alert').show().find('strong').text(
									'errore sulle date');
							$('#dp3').datepicker('hide');
						} else {
							$('#alert').hide();
							endDate = new Date(ev.date);
							$('#endDate').text($('#dp3').data('date'));
						}
						/* 	$('#dp3').datepicker('hide'); */
					});

		});
	</script>

	<div class="datepicker datepicker-dropdown dropdown-menu"
		style="display: none; top: 243.1428680419922px; left: 333.2857360839844px; z-index: 10;">
		<div class="datepicker-days" style="display: block;">
			<table class=" table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">February 2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
					<tr>
						<th class="dow">Su</th>
						<th class="dow">Mo</th>
						<th class="dow">Tu</th>
						<th class="dow">We</th>
						<th class="dow">Th</th>
						<th class="dow">Fr</th>
						<th class="dow">Sa</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="day old">29</td>
						<td class="day old">30</td>
						<td class="day old">31</td>
						<td class="day">1</td>
						<td class="day">2</td>
						<td class="day">3</td>
						<td class="day">4</td>
					</tr>
					<tr>
						<td class="day">5</td>
						<td class="day">6</td>
						<td class="day">7</td>
						<td class="day">8</td>
						<td class="day">9</td>
						<td class="day">10</td>
						<td class="day">11</td>
					</tr>
					<tr>
						<td class="day">12</td>
						<td class="day">13</td>
						<td class="day">14</td>
						<td class="day">15</td>
						<td class="day active">16</td>
						<td class="day">17</td>
						<td class="day">18</td>
					</tr>
					<tr>
						<td class="day">19</td>
						<td class="day">20</td>
						<td class="day">21</td>
						<td class="day">22</td>
						<td class="day">23</td>
						<td class="day">24</td>
						<td class="day">25</td>
					</tr>
					<tr>
						<td class="day">26</td>
						<td class="day">27</td>
						<td class="day">28</td>
						<td class="day">29</td>
						<td class="day new">1</td>
						<td class="day new">2</td>
						<td class="day new">3</td>
					</tr>
					<tr>
						<td class="day new">4</td>
						<td class="day new">5</td>
						<td class="day new">6</td>
						<td class="day new">7</td>
						<td class="day new">8</td>
						<td class="day new">9</td>
						<td class="day new">10</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-months" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="month">Jan</span><span
							class="month active">Feb</span><span class="month">Mar</span><span
							class="month">Apr</span><span class="month">May</span><span
							class="month">Jun</span><span class="month">Jul</span><span
							class="month">Aug</span><span class="month">Sep</span><span
							class="month">Oct</span><span class="month">Nov</span><span
							class="month">Dec</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-years" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2010-2019</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="year old">2009</span><span
							class="year">2010</span><span class="year">2011</span><span
							class="year active">2012</span><span class="year">2013</span><span
							class="year">2014</span><span class="year">2015</span><span
							class="year">2016</span><span class="year">2017</span><span
							class="year">2018</span><span class="year">2019</span><span
							class="year old">2020</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<div class="datepicker datepicker-dropdown dropdown-menu"
		style="display: none;">
		<div class="datepicker-days" style="display: block;">
			<table class=" table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">February 2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
					<tr>
						<th class="dow">Su</th>
						<th class="dow">Mo</th>
						<th class="dow">Tu</th>
						<th class="dow">We</th>
						<th class="dow">Th</th>
						<th class="dow">Fr</th>
						<th class="dow">Sa</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="day old">29</td>
						<td class="day old">30</td>
						<td class="day old">31</td>
						<td class="day">1</td>
						<td class="day">2</td>
						<td class="day">3</td>
						<td class="day">4</td>
					</tr>
					<tr>
						<td class="day">5</td>
						<td class="day">6</td>
						<td class="day">7</td>
						<td class="day">8</td>
						<td class="day">9</td>
						<td class="day">10</td>
						<td class="day">11</td>
					</tr>
					<tr>
						<td class="day">12</td>
						<td class="day">13</td>
						<td class="day">14</td>
						<td class="day">15</td>
						<td class="day active">16</td>
						<td class="day">17</td>
						<td class="day">18</td>
					</tr>
					<tr>
						<td class="day">19</td>
						<td class="day">20</td>
						<td class="day">21</td>
						<td class="day">22</td>
						<td class="day">23</td>
						<td class="day">24</td>
						<td class="day">25</td>
					</tr>
					<tr>
						<td class="day">26</td>
						<td class="day">27</td>
						<td class="day">28</td>
						<td class="day">29</td>
						<td class="day new">1</td>
						<td class="day new">2</td>
						<td class="day new">3</td>
					</tr>
					<tr>
						<td class="day new">4</td>
						<td class="day new">5</td>
						<td class="day new">6</td>
						<td class="day new">7</td>
						<td class="day new">8</td>
						<td class="day new">9</td>
						<td class="day new">10</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-months" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="month">Jan</span><span
							class="month active">Feb</span><span class="month">Mar</span><span
							class="month">Apr</span><span class="month">May</span><span
							class="month">Jun</span><span class="month">Jul</span><span
							class="month">Aug</span><span class="month">Sep</span><span
							class="month">Oct</span><span class="month">Nov</span><span
							class="month">Dec</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-years" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2010-2019</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="year old">2009</span><span
							class="year">2010</span><span class="year">2011</span><span
							class="year active">2012</span><span class="year">2013</span><span
							class="year">2014</span><span class="year">2015</span><span
							class="year">2016</span><span class="year">2017</span><span
							class="year">2018</span><span class="year">2019</span><span
							class="year old">2020</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<div class="datepicker datepicker-dropdown dropdown-menu"
		style="display: none;">
		<div class="datepicker-days" style="display: block;">
			<table class=" table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">February 2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
					<tr>
						<th class="dow">Su</th>
						<th class="dow">Mo</th>
						<th class="dow">Tu</th>
						<th class="dow">We</th>
						<th class="dow">Th</th>
						<th class="dow">Fr</th>
						<th class="dow">Sa</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="day old">29</td>
						<td class="day old">30</td>
						<td class="day old">31</td>
						<td class="day">1</td>
						<td class="day">2</td>
						<td class="day">3</td>
						<td class="day">4</td>
					</tr>
					<tr>
						<td class="day">5</td>
						<td class="day">6</td>
						<td class="day">7</td>
						<td class="day">8</td>
						<td class="day">9</td>
						<td class="day">10</td>
						<td class="day">11</td>
					</tr>
					<tr>
						<td class="day active">12</td>
						<td class="day">13</td>
						<td class="day">14</td>
						<td class="day">15</td>
						<td class="day">16</td>
						<td class="day">17</td>
						<td class="day">18</td>
					</tr>
					<tr>
						<td class="day">19</td>
						<td class="day">20</td>
						<td class="day">21</td>
						<td class="day">22</td>
						<td class="day">23</td>
						<td class="day">24</td>
						<td class="day">25</td>
					</tr>
					<tr>
						<td class="day">26</td>
						<td class="day">27</td>
						<td class="day">28</td>
						<td class="day">29</td>
						<td class="day new">1</td>
						<td class="day new">2</td>
						<td class="day new">3</td>
					</tr>
					<tr>
						<td class="day new">4</td>
						<td class="day new">5</td>
						<td class="day new">6</td>
						<td class="day new">7</td>
						<td class="day new">8</td>
						<td class="day new">9</td>
						<td class="day new">10</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-months" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="month">Jan</span><span
							class="month active">Feb</span><span class="month">Mar</span><span
							class="month">Apr</span><span class="month">May</span><span
							class="month">Jun</span><span class="month">Jul</span><span
							class="month">Aug</span><span class="month">Sep</span><span
							class="month">Oct</span><span class="month">Nov</span><span
							class="month">Dec</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-years" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2010-2019</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="year old">2009</span><span
							class="year">2010</span><span class="year">2011</span><span
							class="year active">2012</span><span class="year">2013</span><span
							class="year">2014</span><span class="year">2015</span><span
							class="year">2016</span><span class="year">2017</span><span
							class="year">2018</span><span class="year">2019</span><span
							class="year old">2020</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<div class="datepicker datepicker-dropdown dropdown-menu"
		style="display: none;">
		<div class="datepicker-days" style="display: block;">
			<table class=" table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">February 2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
					<tr>
						<th class="dow">Su</th>
						<th class="dow">Mo</th>
						<th class="dow">Tu</th>
						<th class="dow">We</th>
						<th class="dow">Th</th>
						<th class="dow">Fr</th>
						<th class="dow">Sa</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="day old">29</td>
						<td class="day old">30</td>
						<td class="day old">31</td>
						<td class="day">1</td>
						<td class="day">2</td>
						<td class="day">3</td>
						<td class="day">4</td>
					</tr>
					<tr>
						<td class="day">5</td>
						<td class="day">6</td>
						<td class="day">7</td>
						<td class="day">8</td>
						<td class="day">9</td>
						<td class="day">10</td>
						<td class="day">11</td>
					</tr>
					<tr>
						<td class="day">12</td>
						<td class="day">13</td>
						<td class="day">14</td>
						<td class="day">15</td>
						<td class="day">16</td>
						<td class="day">17</td>
						<td class="day">18</td>
					</tr>
					<tr>
						<td class="day">19</td>
						<td class="day active">20</td>
						<td class="day">21</td>
						<td class="day">22</td>
						<td class="day">23</td>
						<td class="day">24</td>
						<td class="day">25</td>
					</tr>
					<tr>
						<td class="day">26</td>
						<td class="day">27</td>
						<td class="day">28</td>
						<td class="day">29</td>
						<td class="day new">1</td>
						<td class="day new">2</td>
						<td class="day new">3</td>
					</tr>
					<tr>
						<td class="day new">4</td>
						<td class="day new">5</td>
						<td class="day new">6</td>
						<td class="day new">7</td>
						<td class="day new">8</td>
						<td class="day new">9</td>
						<td class="day new">10</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-months" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="month">Jan</span><span
							class="month active">Feb</span><span class="month">Mar</span><span
							class="month">Apr</span><span class="month">May</span><span
							class="month">Jun</span><span class="month">Jul</span><span
							class="month">Aug</span><span class="month">Sep</span><span
							class="month">Oct</span><span class="month">Nov</span><span
							class="month">Dec</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-years" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2010-2019</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="year old">2009</span><span
							class="year">2010</span><span class="year">2011</span><span
							class="year active">2012</span><span class="year">2013</span><span
							class="year">2014</span><span class="year">2015</span><span
							class="year">2016</span><span class="year">2017</span><span
							class="year">2018</span><span class="year">2019</span><span
							class="year old">2020</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<div class="datepicker datepicker-dropdown dropdown-menu"
		style="display: none;">
		<div class="datepicker-days" style="display: block;">
			<table class=" table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">February 2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
					<tr>
						<th class="dow">Su</th>
						<th class="dow">Mo</th>
						<th class="dow">Tu</th>
						<th class="dow">We</th>
						<th class="dow">Th</th>
						<th class="dow">Fr</th>
						<th class="dow">Sa</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="day old">29</td>
						<td class="day old">30</td>
						<td class="day old">31</td>
						<td class="day">1</td>
						<td class="day">2</td>
						<td class="day">3</td>
						<td class="day">4</td>
					</tr>
					<tr>
						<td class="day">5</td>
						<td class="day">6</td>
						<td class="day">7</td>
						<td class="day">8</td>
						<td class="day">9</td>
						<td class="day">10</td>
						<td class="day">11</td>
					</tr>
					<tr>
						<td class="day">12</td>
						<td class="day">13</td>
						<td class="day">14</td>
						<td class="day">15</td>
						<td class="day">16</td>
						<td class="day">17</td>
						<td class="day">18</td>
					</tr>
					<tr>
						<td class="day">19</td>
						<td class="day">20</td>
						<td class="day">21</td>
						<td class="day">22</td>
						<td class="day">23</td>
						<td class="day">24</td>
						<td class="day active">25</td>
					</tr>
					<tr>
						<td class="day">26</td>
						<td class="day">27</td>
						<td class="day">28</td>
						<td class="day">29</td>
						<td class="day new">1</td>
						<td class="day new">2</td>
						<td class="day new">3</td>
					</tr>
					<tr>
						<td class="day new">4</td>
						<td class="day new">5</td>
						<td class="day new">6</td>
						<td class="day new">7</td>
						<td class="day new">8</td>
						<td class="day new">9</td>
						<td class="day new">10</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-months" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2012</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="month">Jan</span><span
							class="month active">Feb</span><span class="month">Mar</span><span
							class="month">Apr</span><span class="month">May</span><span
							class="month">Jun</span><span class="month">Jul</span><span
							class="month">Aug</span><span class="month">Sep</span><span
							class="month">Oct</span><span class="month">Nov</span><span
							class="month">Dec</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<div class="datepicker-years" style="display: none;">
			<table class="table-condensed">
				<thead>
					<tr>
						<th class="prev" style="visibility: visible;"><i
							class="icon-arrow-left"></i></th>
						<th colspan="5" class="switch">2010-2019</th>
						<th class="next" style="visibility: visible;"><i
							class="icon-arrow-right"></i></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="7"><span class="year old">2009</span><span
							class="year">2010</span><span class="year">2011</span><span
							class="year active">2012</span><span class="year">2013</span><span
							class="year">2014</span><span class="year">2015</span><span
							class="year">2016</span><span class="year">2017</span><span
							class="year">2018</span><span class="year">2019</span><span
							class="year old">2020</span></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" class="today" style="display: none;">Today</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>

