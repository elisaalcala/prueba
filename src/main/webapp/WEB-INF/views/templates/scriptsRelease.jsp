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
    
    // Agrega el evento de clic a cada fila de la tabla
    document.addEventListener('DOMContentLoaded', function () {
    
        document.querySelectorAll('.proyecto-row-per-release').forEach(row => {
            row.addEventListener('click', () => {
                // Obtener el ID del proyecto desde el atributo data-proyecto-id de la fila
                const proyectoId = row.getAttribute('data-proyecto-id-release');
                const projectInfoElement = document.querySelector('.project-info');
                
                function checkNull(value) {
                    if (value === null || value === undefined) {
                        return 'Sin  asignar';
                    } else {
                        return value;
                    }
                }

                if (proyectoId) {
                    var url = '/projects/' + proyectoId +'/find';
                    // Rellenar el HTML con la información del proyecto
                    fetch(url)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('No se pudo obtener el proyecto');
                            }
                            return response.json();
                        })
                        .then(project => {
                            // Procesar el proyecto obtenido
                            console.log('Proyecto encontrado:', project);
                            projectInfoElement.innerHTML = `
                            <div class="project-info margin-project-in-release">
                                <a class="project-in-release-detail-heading color-cyan" href="/projects/`+ project.idProject +`">
                                    <i class="fa-solid fa-chevron-down"></i>
                                    <span>` + project.nameProject + `-` +project.titleProject + `</span>
                                </a>
                                    <div class="row margin-top-10">
                                    <div class="col">
                                    <div class="ticket-info">
                                        <div class="ticket-detail-heading color-cyan">
                                        Detalles
                                        </div>
                                        <div class="row">
                                            <dt class="col">Alcance:</dt>
                                            <dd class="col" id="ticketType">`+project.typeProject+`</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Prioridad:</dt>
                                            <dd class="col" id="ticketPriority">`+project.priorityProject+`</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Entorno prueba:</dt>
                                            <dd class="col" id="ticketEnvironment">`+project.environmentProject+`</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Estado actual:</dt>
                                            <dd class="col" id="ticketStatus">`+project.statusProject+`</dd>
                                        </div>
                                    </div>
                                    </div>
                                    
                                    <div class="col">
                                        <div class="ticket-info">
                                        <div class="ticket-detail-heading color-cyan">
                                            Personal
                                        </div>
                                        <div class="row">
                                            <dt class="col">Empleado Asignado:</dt>
                                            
                                                <dd class="col" id="employeeUserAssign">`+ checkNull(project.employeeUserAssign) +`</dd>
                                                
                                            
                                        </div>
                                        <div class="row">
                                            <dt class="col">Equipo Asignado:</dt>
                                            <dd class="col" id="teamNameAssign">`+project.teamNameAssign+`</dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Empleado creador:</dt>
                                            <dd class="col" id="employeeUserCreation">`+project.employeeUserCreation+`</dd>
                                        </div>
                                        
                                        </div>
                                    </div>
                                    
                                    <div class="col">
                                        <div class="ticket-info">
                                        <div class="ticket-detail-heading color-cyan">
                                            Fechas
                                        </div>
                                        <div class="row">
                                            <dt class="col">Creación:</dt>
                                            <dd class="col" id="initialDate"></dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Última modificación:</dt>
                                            <dd class="col" id="modifyDate"></dd>
                                        </div>
                                        <div class="row">
                                            <dt class="col">Finalización:</dt>
                                            <dd class="col" id="finishDate"></dd>
                                        </div>
                                        </div>
                                    </div>
                                    </div>
                                    
                                    <div class="ticket-info">
                                    <div class="ticket-detail-heading color-cyan">
                                        Descripcion
                                    </div>
                                    <div class="row textarea-height">
                                        <textarea class="form-control" readonly>`+project.descriptionProject+`</textarea>
                                </div>
                            </div>`;

                            function formatDateTime(dateTime) {
                                var options = { 
                                    year: 'numeric', 
                                    month: '2-digit', 
                                    day: '2-digit', 
                                    hour: '2-digit', 
                                    minute: '2-digit', 
                                    second: '2-digit' 
                                };
                                return new Date(dateTime).toLocaleString(undefined, options);
                            }

                            // Función para comprobar si la fecha es nula y devolver un mensaje apropiado
                            function formatFinishDate(finishDate) {
                                if (finishDate == null) {
                                    return "No finalizado";
                                } else {
                                    return formatDateTime(finishDate);
                                }
                            }
                            
                            // Formatear las fechas
                            var formattedInitialDate = formatDateTime(project.initialDate);
                            var formattedModifyDate = formatDateTime(project.modifyDate);
                            var formattedFinishDate = formatFinishDate(project.finishDate);

                            // Actualizar el contenido HTML con las fechas formateadas
                            document.getElementById("initialDate").textContent = formattedInitialDate;
                            document.getElementById("modifyDate").textContent = formattedModifyDate;
                            document.getElementById("finishDate").textContent = formattedFinishDate;

                        })
                        .catch(error => {
                            console.error('Error al obtener el proyecto:', error);
                            // Manejar errores de forma adecuada
                        });
                }
            });
        });
    });

    //Date Format
    document.addEventListener("DOMContentLoaded", function() {
        // Función para formatear la fecha en el formato deseado
        function formatDateTime(dateTime) {
            var options = { 
                year: 'numeric', 
                month: '2-digit', 
                day: '2-digit'
            };

            if (dateTime == ``) {
                return ` `;
            }else{
                return new Date(dateTime).toLocaleString(undefined, options);
            }
            
        }

        // Formatear las fechas
        var formattedInitialDate = formatDateTime(`${release.initialDate}`);
        var formattedrequirementsDate = formatDateTime(`${release.requirementsDate}`);
        var formattedprjAssignmentDate = formatDateTime(`${release.prjAssignmentDate}`);
        var formatteddevelopDate = formatDateTime(`${release.developDate}`);
        var formattedtstDate = formatDateTime(`${release.tstDate}`);
        var formatteduatDate = formatDateTime(`${release.uatDate}`);
        var formattedproDate = formatDateTime(`${release.proDate}`);


        // Actualizar el contenido HTML con las fechas formateadas
        document.getElementById("initialDate").textContent = formattedInitialDate;
        document.getElementById("requirementsDate").textContent = formattedrequirementsDate;
        document.getElementById("prjAssignmentDate").textContent = formattedprjAssignmentDate;
        document.getElementById("developDate").textContent = formatteddevelopDate;
        document.getElementById("tstDate").textContent = formattedtstDate;
        document.getElementById("uatDate").textContent = formatteduatDate;
        document.getElementById("proDate").textContent = formattedproDate;

    });

    //Create Project
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('saveProjectButton').addEventListener('click', function() {
            
            // Obtener los valores editados del formulario
            var url = `/projects/create`;
            var createProjectTitle = document.getElementById('createProjectTitle').value;
            var createProjectDescription = document.getElementById('createProjectDescription').value;
            var createProjectType = document.getElementById('createProjectType').value;
            var createProjectPriority = document.getElementById('createProjectPriority').value;
            var createProjectEnvironment = document.getElementById('createProjectEnvironment').value;
            var createProjectTeamNameAssign = document.getElementById('createProjectTeamNameAssign').value;
            
            // Crear un objeto con los datos actualizados del ticket
            var createProject = {
                titleProject: createProjectTitle,
                descriptionProject: createProjectDescription,
                typeProject: createProjectType,
                priorityProject: createProjectPriority,
                environmentProject: createProjectEnvironment,
                teamNameAssign: createProjectTeamNameAssign,
                releaseName: `${release.nameRelease}`
            };
            
            // Realizar la solicitud POST para crear el ticket
            fetch(url, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json'
                },
                body: JSON.stringify(createProject)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al crear el ticket');
                }
                // Si es exitosa, cerrar el modal
                var createModalProject = bootstrap.Modal.getInstance(document.getElementById('createModalProject'));
                createModalProject.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                return response.json();
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });
   
    //Delete Project
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('deleteTicketButtonRelease').addEventListener('click', function() {
            var url = `/releases/${release.idRelease}/delete`;
            
            // Realizar la solicitud DELETE para eliminar el ticket
            fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(`${release.idRelease}`)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el ticket');
                }
                var modalHeader = document.getElementById('modal-header-delete-release');
                var modalBody = document.getElementById('modal-body-delete-release');
                var modalFooter = document.getElementById('modal-footer-delete-release');
                modalBody.classList.add('d-none'); // Ocultar el modal
                modalFooter.classList.add('d-none'); 
                modalHeader.classList.add('d-none');

                // Si la eliminación es exitosa, mostrar mensaje de éxito y ocultar el cuerpo y el pie de página del modal
                document.getElementById('successMessageDeleteRelease').style.display = 'block';

                // Cerrar el modal después de 3 segundos y redirigir a la página relevante
                setTimeout(function(){
                    var deleteModalProject = new bootstrap.Modal(document.getElementById('deleteModalRelease'));
                    deleteModalProject.hide();
                    
                }, 3000);
                return response.json();
                
            })
            .then(data => {
                // Redirigir al método GET utilizando la URL proporcionada en la respuesta JSON
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    });

    //Edit Release
    document.addEventListener('DOMContentLoaded', (event) => {
        document.getElementById('updateReleaseButton').addEventListener('click', function() {
            // Get edited values from the form
            var url = `/releases/${release.idRelease}/edit`; // Assuming 'release' has the release ID

            var editInitialDate = document.getElementById('editInitialDate').value;
            var editRequirementsDate = document.getElementById('editRequirementsDate').value;
            var editPrjAssignmentDate = document.getElementById('editPrjAssignmentDate').value;
            var editDevelopDate = document.getElementById('editDevelopDate').value;
            var editTstDate = document.getElementById('editTstDate').value;
            var editUatDate = document.getElementById('editUatDate').value;
            var editProDate = document.getElementById('editProDate').value;

            // Create an object with the updated release data
            var editedRelease = {
            initialDate: editInitialDate,
            requirementsDate: editRequirementsDate,
            prjAssignmentDate: editPrjAssignmentDate,
            developDate: editDevelopDate,
            tstDate: editTstDate,
            uatDate: editUatDate,
            proDate: editProDate
            };

            // Make a PUT request to update the release
            fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editedRelease)
            })
            .then(response => {
                if (!response.ok) {
                throw new Error('Error al actualizar la release');
                }
                // If update is successful, close the modal
                var editModalRelease = bootstrap.Modal.getInstance(document.getElementById('editModalRelease'));
                editModalRelease.hide();
                document.body.classList.remove('modal-open');
                document.querySelector('.modal-backdrop').remove();
                return response.json();
            })
            .then(data => {
                window.location.href = data.redirectUrl;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
        });

</script>