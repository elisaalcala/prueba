<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    //Calendar
    document.addEventListener('DOMContentLoaded', function () {
        const daysTag = document.querySelector(".days"),
        currentDate = document.querySelector(".current-date"),
        prevNextIcon = document.querySelectorAll(".icons span");

        let date = new Date(),
        currYear = date.getFullYear(),
        currMonth = date.getMonth();

        
        const months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio","Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
        
        function isSameDay(date1, date2) {
            return date1.getFullYear() === date2.getFullYear() &&
                date1.getMonth() === date2.getMonth() &&
                date1.getDate() === date2.getDate();
        }


        const renderCalendar = () => {
            
            let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(), 
            lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(), 
            lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(), 
            lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate(); 
            let liTag = "";

            for (let i = firstDayofMonth; i > 0; i--) { // creating li of previous month last days
                let ad = lastDateofLastMonth - i + 1;
                liTag += '<li class="inactive">' + ad + '</li>';
            }

            for (let i = 1; i <= lastDateofMonth; i++) {
                // Comparación de fecha para cada una de las fechas del proyecto
                let isInitialDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.initialDate}`));
                let isRequirementsDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.requirementsDate}`));
                let isPrjAssignmentDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.prjAssignmentDate}`));
                let isDevelopDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.developDate}`));
                let isTstDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.tstDate}`));
                let isUatDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.uatDate}`));
                let isProDate = isSameDay(new Date(currYear, currMonth, i), new Date(`${release.proDate}`));


                // Agregar clases de acuerdo a las fechas del proyecto
                let classForDate = '';
                if (isInitialDate) classForDate = 'initial-date" data-tooltip="Inicio de release ';
                else if (isRequirementsDate) classForDate = 'requirements-date " data-tooltip="Captura de requisitos';
                else if (isPrjAssignmentDate) classForDate = 'prj-assignment-date " data-tooltip="Asignacion de proyectos';
                else if (isDevelopDate) classForDate = 'develop-date " data-tooltip="Inicio de desarrollo';
                else if (isTstDate) classForDate = 'tst-date " data-tooltip="Inicio de pruebas TST';
                else if (isUatDate) classForDate = 'uat-date " data-tooltip="Inicio de pruebas UAT';
                else if (isProDate) classForDate = 'pro-date " data-tooltip="Subida a PRO';

                // Agregar clase 'active' si es el día actual
                let isToday = i === date.getDate() && currMonth === new Date().getMonth() && currYear === new Date().getFullYear() ? 'active' : '';

                // Concatenar las clases y el día en la etiqueta li
                liTag += '<li class="' + isToday + ' ' + classForDate + '">' + i + '</li>';
            }


            for (let i = lastDayofMonth; i < 6; i++) { // creating li of next month first days
                let d = i - lastDayofMonth + 1;
                liTag += '<li class="inactive">' + d  + '</li>';
            }
            currentDate.innerText = months[currMonth] +'  '+ currYear; // passing current mon and yr as currentDate text
            daysTag.innerHTML = liTag;
        }
        renderCalendar();

        prevNextIcon.forEach(icon => { 
            icon.addEventListener("click", () => { 
                // if clicked icon is previous icon then decrement current month by 1 else increment it by 1
                debugger
                currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;

                if(currMonth < 0 || currMonth > 11) { // if current month is less than 0 or greater than 11
                    // creating a new date of current year & month and pass it as date value
                    date = new Date(currYear, currMonth, new Date().getDate());
                    currYear = date.getFullYear(); 
                    currMonth = date.getMonth(); 
                } else {
                    date = new Date(); 
                }
                renderCalendar();
            });
        });
    });
    
    //Chart Carga Trabajo
    $(document).ready(function() {
        
        const canvas = document.getElementById("chart-carga-trabajo");
        const ctx = canvas.getContext('2d');
        const userEmployee = JSON.parse(canvas.getAttribute('user'));
        const load = JSON.parse(canvas.getAttribute('load'));

        function generateRandomBrightColors(count) {
            const predefinedColors = [
                'rgb(125, 30, 30)',    // Rojo
                'rgb(141, 242, 78)',    // Verde
                'rgb(110, 216, 219)',    // Azul
                'rgb(255, 230, 87)',  // Amarillo
                'rgb(217, 135, 41)',  // Naranja
                'rgb(179, 116, 116)', // Rosa
                'rgb(121, 110, 219)'   // Morado
            ];

            const randomColors = [];
            for (let i = 0; i < count; i++) {
                const randomIndex = Math.floor(Math.random() * predefinedColors.length);
                randomColors.push(predefinedColors[randomIndex]);
            }

            return randomColors;
        }


        // Llamamos a la función para generar colores aleatorios
        const randomColors = generateRandomBrightColors(userEmployee.length);

        // Creamos el gráfico con los colores aleatorios
        const myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: userEmployee,
                datasets: [{
                    label: 'Carga por Persona',
                    data: load,
                    backgroundColor: randomColors, 
                    hoverOffset: 4
                }]
            }
        });
    });

    //Dropdown Dailywork
    document.addEventListener('DOMContentLoaded', (event) => {
        document.querySelectorAll('.createProjectLink').forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
                var releaseId = this.getAttribute('data-release-id');
                var targetModal = this.getAttribute('data-target');

                // Guardar el targetModal en el localStorage para usarlo después de la redirección
                localStorage.setItem('openModal', targetModal);

                // Redirigir a la página
                window.location.href = `/releases/`+ releaseId;
            });
        });

        document.querySelectorAll('.deleteProjectLink').forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault(); // Prevenir el comportamiento predeterminado del enlace
                var releaseId = this.getAttribute('data-release-id');
                var targetModal = this.getAttribute('data-target');

                // Guardar el targetModal en el localStorage para usarlo después de la redirección
                localStorage.setItem('openModal', targetModal);

                // Redirigir a la página
                window.location.href = `/releases/`+ releaseId;
            });
        });
        
        // Verificar si hay un modal para abrir después de la redirección
        var openModal = localStorage.getItem('openModal');
        if (openModal) {
            // Abrir el modal
            var myModal = new bootstrap.Modal(document.querySelector(openModal));
            myModal.show();

            // Limpiar el localStorage
            localStorage.removeItem('openModal');
        }
    });
    
    // Función para controlar los paneles de colapso del la barra lateral
    document.addEventListener("DOMContentLoaded", function() {
        function togglePanel(panelLinkSelector, panelSelector) {
            var panelLink = document.querySelector(panelLinkSelector);
            var panel = document.querySelector(panelSelector);

            panelLink.addEventListener('click', function() {
                if (panel.classList.contains('show')) {
                    panel.classList.remove('show');
                    panelLink.classList.add('collapsed');
                } else {
                    panel.classList.add('show');
                    panelLink.classList.remove('collapsed');
                }
            });
        }

        // Controlar el panel de Estadísticas
        togglePanel('.nav-link[data-target="#collapseCargaTrabajo"]', '#collapseCargaTrabajo');
    });

</script>