<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>

/* Restablecer estilos */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  .login{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
  }
  /* Contenedor principal */
  .container {
    display: flex;
    flex-direction: row;
    min-height: 100vh;
    max-width: 100%;
    padding-left: 0px;
    padding-right: 0px;
  
  }
  .me-2 {
      margin-right: .5rem !important;
  }
   
  main {
    flex: 1;
    }
  
  /* Navegación lateral */
  
  .bg-gradient-primary {
      background-color: #00838f;
      background-image: linear-gradient(180deg, #018e9b 10%, #006169 100%);
      background-size: cover;
  }
  .sidebar hr.sidebar-divider {
      border-top: 1px solid rgba(255, 255, 255, 0.329);
  }
  
  @media (min-width: 768px){
  .sidebar {
      width: 14.5rem !important;
  }
  }
  @media (min-width: 768px){
  .sidebar .nav-item .nav-link[data-toggle=collapse]::after {
      width: 1rem;
      text-align: center;
      float: right;
      vertical-align: 0;
      border: 0;
      font-weight: 900;
      content: '\f107';
      font-family: 'Font Awesome 5 Free';
  }
  }
  .nav-item{
    padding-left: 15px;
  }
  .navbar-nav{
    padding-right: 20px;
    padding-left: 20px;
  }
  .nav-color, .nav-colorlink {
    color: rgba(255, 255, 255, 0.575);
  }
  .nav-colorlink:hover, 
  .sidebar-brand-color:hover {
      color: #fff;
  }
  .sidebar-heading{
    font-weight: 800;
    font-size: .75rem;
    text-transform: uppercase;
  }
  .sidebar-brand {
        color: rgba(255, 255, 255, 0.966);
      height: 4.375rem;
      font-size: 1.3rem;
      font-weight: 900;
      text-transform: uppercase;
      letter-spacing: .05rem;
  }
  .mt-0{
    margin-top: 0rem;
  }
  .collapse-header {
      margin: 0;
      padding: .3rem 1rem;
      text-transform: uppercase;
      font-weight: 800;
      font-size: .70rem;
      color: #b7b9cc;
  }
  .collapse-item {
      padding: .5rem 1rem;
      margin: 0 .5rem;
      display: block;
      color: #3a3b45;
      text-decoration: none;
      border-radius: .35rem;
      white-space: nowrap;
  }
  .collapse-item:hover {
    background-color: #84c8cee3;
    text-decoration: none;
    color : #00838f;
  }
  .bg-clear{
      background-color: #c7c7c771;
  }
  
  /* Cabecera */

  
  /* Navegación superior */
  .navegacion-superior {
      background-color: #f0f0f0;
      padding: 5px;
      display: flex;
      justify-content: space-around;
  }
  
  .navegacion-superior a {
    text-decoration: none;
    color: #333;
    margin: 0 10px;
  }
  
  /* Contenido principal */
  .container-fluid {
      padding-left: 1.5rem;
      padding-right: 1.5rem;
      width: 100%;
      margin-right: auto;
      margin-left: auto;
  }
  
  /* Título del contenido */
  .titulo {
    padding: 10px;
    padding-left: 0px;
  }
  
  /* Columnas */
  .columnas {
    display: flex;
    flex-wrap: wrap;
  
  }
  .columnas-izquierda {
    display: flex;
    flex-wrap: wrap;
    flex: 70%;
    flex-direction: column; 
  
  }
  .columnas-derecha {
    display: flex;
    flex-wrap: wrap;
    flex: 25%;
    flex-direction: column; 
  }
  .align_columns{
    align-content: center;
  }

  .m-column{
      margin-bottom: 10px;
      margin-top: 10px;
  }
  .m-left{
      margin-left: 10px;
  }
  .m-right{
      margin-right: 10px;
  }
  .m-top{
      margin-top: 10px;
  }
  .m-bottom{
      margin-bottom: 10px;
  }
  .div-col{
    display: flex;
    flex-direction: row;
  }
  .col-margin-top-left{
    margin-left: 20px;
    margin-top: 10px;
  }
  /*Botones*/
  .bg-cyan-800 {
      background-color: #00838fda;
      border-color: #00838fda;
      color: white;
  }
  .bg-cyan-800:hover{
      background-color: #88d4dad2;
      color: #00838f;
  }
  /* Tablas */
  table {
    width: 100%;
    border-collapse: collapse;
  }
  
  th, td {
    border: 1px solid #ddd;
    padding: 5px;
  }
  
  .dropdown-toggle.table::after {
      content: none; 
      border: none; 
  }
  .dt-layout-row {
    display: flex; 
    justify-content: space-between;
    margin-top: 10px;
  }
  .dt-layout-table{
    display: block;
  }
  .dt-paging-button {
    padding: 3px;
    padding-right: 6px;
    padding-left: 6px;
    border: 1px solid #ffffff;
    background-color: #e1f4f5;
    color: #00838f;
}
.dt-paging-button:hover {
  background-color: #98dfe3;
}
.first{
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}
.last, .next, .current, .previous{
  border-left-width: 0px;
}
.last{
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}


div.dt-container div.dt-info {
    padding-top: 0px;
}
div.dt-container div.dt-length select {
    margin-right: 0px;
}
div.dt-container div.dt-search input:focus {
    border: 2px solid #00838f;
    outline: none; /* Evita el resaltado predeterminado en algunos navegadores */
    padding-left: 3px;
}
.pointer-row:hover {
      cursor: pointer;
    }
.color-cyan{
    color: #00838f;
}
.color-projects{
  color: #6100b1;
}
.bg-projects{
  background-color: #6100b1;
}
.bg-incidences{
  background-color: #dd3400;
}
.bg-charts{
  background-color: #a243a5;
}
.bg-finish{
  background-color: #69c52f;
}
.color-incidences{
  color: #dd3400;
}
.padding-0{
  padding: 0%;
}
 /*Pagina Ticket*/
 .ticket-detail-heading{
    font-weight: 900;
    font-size: .9rem;
    text-transform: uppercase;
  }
  .ticket-info .row, .margin-left-15{
    margin-left: 15px;

  }
  .ticket-info{
    margin-bottom: 15px;
  }
  .flex-wrap-nowrap{
    flex-wrap: nowrap;
  }
  .assignedEmployee {
        width: 68%; 
    }
  .assignedTeam {
        width: 100%; 
    }
  .textarea-height{
    height: 300px;
  }
  /* Footer */
  .sticky-footer {
      padding: 2rem 0;
      flex-shrink: 0;
  }
  .copyright {
      line-height: 1;
      font-size: .8rem;
  }
  
  /* Calendar */
  .wrapper{
  width: 450px;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 15px 40px rgba(0,0,0,0.12);
}
.wrapper header{
  background-color: #00838fda;
  border-color: #00838fda;
  color: white;
  display: flex;
  align-items: center;
  padding: 20px 30px 20px;
  justify-content: space-between;
}
header .icons{
  display: flex;
}
header .icons span{
  height: 38px;
  width: 38px;
  margin: 0 1px;
  cursor: pointer;
  color: white;
  text-align: center;
  line-height: 38px;
  font-size: 1.9rem;
  user-select: none;
  border-radius: 50%;
}
.icons span:last-child{
  margin-right: -10px;
}
header .icons span:hover{
  background-color: #84c8cee3;
  color : #00838f;
}
header .current-date{
  font-size: 1.45rem;
  font-weight: 500;
}
.calendar{
  padding: 20px;
}
.calendar ul{
  display: flex;
  flex-wrap: wrap;
  list-style: none;
  text-align: center;
}
.calendar .days{
  margin-bottom: 20px;
}
.calendar li{
  color: #333;
  width: calc(100% / 7);
  font-size: 1.07rem;
}
.calendar .weeks li{
  font-weight: 500;
  cursor: default;
}
ul.weeks, .margin-bottom-0 {
    margin-bottom: 0px;
}
.calendar .days li{
  z-index: 1;
  cursor: pointer;
  position: relative;
  margin-top: 30px;
}
.days li.inactive{
  color: #aaa;
}
.days li.active, 
.days li.initial-date,
.days li.requirements-date,
.days li.prj-assignment-date,
.days li.develop-date,
.days li.tst-date,
.days li.uat-date,
.days li.pro-date{
  color: #fff;
}

.days li::before{
  position: absolute;
  content: "";
  left: 50%;
  top: 50%;
  height: 40px;
  width: 40px;
  z-index: -1;
  border-radius: 50%;
  transform: translate(-50%, -50%);
}
.days li.active::before{
  background: #00838f;
}
.days li:not(.active):not(.initial-date):not(.requirements-date):not(.prj-assignment-date):not(.develop-date):not(.tst-date):not(.uat-date):not(.pro-date):hover::before {
  background: #f2f2f2; 
}

.initial-date::before {
  background: #69c52f;
}

.requirements-date::before {
  background: #b5bf00;
}

.prj-assignment-date::before {
  background: #b351ab;
}

.develop-date::before {
  background: #009ad6;
}

.tst-date::before {
  background: #860eff;
}

.uat-date::before {
  background: #c67400;
}

.pro-date::before{
  background: #ff0000;
}
.cal-margins{
  margin-left: 20px;
  margin-bottom: 20px;
}


.initial-date:hover::after, .requirements-date:hover::after ,
 .prj-assignment-date:hover::after, .develop-date:hover::after,
 .tst-date:hover::after, .uat-date:hover::after, .pro-date:hover::after {
  content: attr(data-tooltip); 
  background-color: #3c3c3cc7;
  color: #fff;
  padding: 5px 10px;
  border-radius: 5px;
  position: absolute;
  bottom: 100%; 
  left: 50%;
  transform: translateX(-50%);
  white-space: nowrap; 
  opacity: 0; /* Lo hace invisible por defecto */
  transition: opacity 0.3s ease; /* Agrega una transición suave */
}

.initial-date:hover::after, .requirements-date:hover::after ,
 .prj-assignment-date:hover::after, .develop-date:hover::after,
 .tst-date:hover::after, .uat-date:hover::after, .pro-date:hover::after {
  opacity: 1;
}

/*Project in Release*/
.project-in-release-detail-heading{
  font-weight: 800;
    font-size: 1.3rem;
    text-transform: uppercase;
    text-decoration: none;
    margin-bottom: 20px;
    margin-left: -20px;
}
.project-in-release-detail-heading:hover{
  color: #5ba1a8;
  text-decoration: none;
}
.margin-project-in-release{
  margin-left: 20px;
  margin-right: 20px;
}
.margin-top-10{
  margin-top: 10px;
}

/*Control de trabajo*/
.accordion-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.right-content{
  margin-right: 20px;
}
.left-content {
  flex: 1;
}

.accordion-body {
    padding: 7px;
}
.link-text{
  color: #00838f;
  text-decoration: none;
  text-transform: uppercase;
}
.link-text:hover {
    color: #143f44;
    text-decoration: none; 
}
.carga-tittle {
  font-weight: 700;
}
.carga{
  margin-left: 10px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
}

.margin-timeline{
  margin-top: 20px;
  margin-right: 20px;
  margin-left: 30px;
}

.margin-30{
  margin-left: 30px;
}

.flex-40 {
  flex: 40%;
}
.subtitulo{
  font-size: 1.8rem;
  margin-left: 40px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.subtitulo-size{
  font-size: 1.8rem;
}
.m-50{
  margin-left: 50px;
}
</style>