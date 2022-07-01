<div class="readme_title">

<h1 align="center">Requisição Mercado Livre</h1>
<hr>
</div>
<div class="body_text">

<h5>Objetivo do projeto</h5>
<h6> 
O objetivo é fazer uma requisição a pagina do mercado livre a busca de um produto e o programa deve retornar
o nome e o preco do primeiro produto da pagina.<br><br>
O Mercado Livre atua com dois tipos de layout sendo um Stack e um Grid, o programa devera fazer uma validação
para caso o Grid não esteja presente ele chamara a função que retornara o objeto dentro da Stack.<br><br>
Validações que falei a pena adicionar é caso o produto esteja em promoção e caso sim ele retorna os 2 valores, sendo o
inicial e o promocional.<br><br>
A interface é opcional.</h6>

<h4>Grid Layout</h4>
<h6>
O layout grid se encontra dentro da ol class="ui-search-layout ui-search-layout--grid", e deve de ser buscado aqui a lista
dos produtos pegando o nome dele que se encontra dentro da h2 class="ui-search-item__title ui-search-item__group__element" e
o span class="price-tag-amount".<br>
Caso exista o valor promocional sera encontrado dentro de span class="price-tag ui-search-price__part".
</h6>
<img src="img.png">
<hr>
<h6>
O layout stack se encontra dentro da ol class="ui-search-layout ui-search-layout--stack", e deve de se buscar aqui tambem a lista dos
produtos, o nome se encontra dentro de h2 class="ui-search-item__title" e seu valores dentro de span class="price-tag-amount"
e caso exista valor promocional span class="price-tag ui-search-price__part".
</h6>
<h4>Stack</h4>
<img src="img_1.png">
<hr>

<h5> Dependencias adicionais </h5>
<h6> htmlunit 2.62.0 <br> jackson.core 2.13.3 (jackson-databind)</h6>

<h5> Artigos de base</h5>
<a href="https://escoladedados.org/tutoriais/xpath-para-raspagem-de-dados-em-html/"> Introdução a Raspagem de Dados. </a>
</div>