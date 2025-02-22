// Variables globales para almacenar los datos de espacios y reservas
let espaciosGlobal = [];
let reservationsGlobal = [];

/* Función para mostrar mensajes de alerta */
function mostrarAlerta(mensaje, tipo = 'success') {
    const alertContainer = document.getElementById('alert-container');
    // Se utiliza bg-green-500 para success y bg-red-500 para error (Tailwind CSS)
    alertContainer.innerHTML = `<div class="bg-${tipo === 'success' ? 'green' : 'red'}-500 text-white p-4 rounded-lg">${mensaje}</div>`;
    alertContainer.classList.remove('hidden');
    setTimeout(() => {
        alertContainer.classList.add('hidden');
    }, 3000);
}

/*Múltiples Funciones*/

function validarFormulario(nombre, tipo, capacidad, disponibilidad) {
    if (!nombre || !tipo || !capacidad || !disponibilidad) {
        mostrarAlerta('Todos los campos obligatorios deben ser completados', 'error');
        return false;
    }
    return true;
}

/* ========================================
Sección de ESPACIOS
   ======================================== */

/* Obtener espacios desde SpringBoot */
async function fetchEspacios() {
    try {
        const response = await fetch('/api/espacios');
        if (!response.ok) throw new Error('Error al obtener los espacios');
        espaciosGlobal = await response.json();
        return espaciosGlobal;
    } catch (error) {
        mostrarAlerta('Error al cargar los espacios', 'error');
        return [];
    }
}

/* Mostrar espacios en la tabla */
async function mostrarEspacios() {
    await fetchEspacios();
    const lista = document.getElementById('resource-list');
    lista.innerHTML = '';

    espaciosGlobal.forEach(espacio => {
        const fila = document.createElement('tr');
        // Se asigna el id del espacio en el atributo data-id
        fila.setAttribute('data-id', espacio.id);
        fila.innerHTML = `
        <td>${espacio.nombre}</td>
        <td>${espacio.tipo}</td>
        <td>${espacio.capacidad}</td>
        <td>${espacio.disponibilidad}</td>
        <td>
            <button class="btn btn-sm btn-warning" data-id="${espacio.id}" onclick="editarEspacio(this)">Editar</button>
            <button class="btn btn-sm btn-danger" data-id="${espacio.id}" onclick="eliminarEspacio(this)">Eliminar</button>
        </td>
        `;
        lista.appendChild(fila);
    });
}

/* Envío del formulario de espacios: crear o actualizar */
document.getElementById('space-form').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const id = document.getElementById('space-id').value;
    const nombre = document.getElementById('space-name').value;
    const tipo = document.getElementById('type').value;
    const capacidad = document.getElementById('capacity').value;
    const disponibilidad = document.getElementById('disponibility').value;

    if (!validarFormulario(nombre, tipo, capacidad, disponibilidad)) return;

    const espacio = { nombre, tipo, capacidad, disponibilidad };

    try {
        let response;
        if (id) {
        // Actualización de espacio
        response = await fetch(`/api/espacios/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(espacio)
        });
        } else {
        // Creación de espacio
        response = await fetch('/api/espacios', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(espacio)
        });
        }
        if (!response.ok) throw new Error('Error al guardar el espacio');
        mostrarAlerta('Espacio guardado exitosamente');
        // Reinicia el formulario y limpia el campo oculto
        document.getElementById('space-form').reset();
        document.getElementById('space-id').value = '';
        await mostrarEspacios();
        populateReservationSpaces(); // Actualiza el select de espacios en reservas
    } catch (error) {
        mostrarAlerta(error.message, 'error');
    }
});

/* Función para editar un espacio */
async function editarEspacio(boton) {
    const id = boton.getAttribute('data-id');
    try {
        const response = await fetch(`/api/espacios/${id}`);
        if (!response.ok) throw new Error('Error al cargar el espacio');
        const espacio = await response.json();
        document.getElementById('space-id').value = espacio.id;
        document.getElementById('space-name').value = espacio.nombre;
        document.getElementById('type').value = espacio.tipo;
        document.getElementById('capacity').value = espacio.capacidad;
        document.getElementById('disponibility').value = espacio.disponibilidad;
    } catch (error) {
        mostrarAlerta(error.message, 'error');
    }
}

/* Función para eliminar un espacio */
async function eliminarEspacio(boton) {
    const id = boton.getAttribute('data-id');
    try {
        const response = await fetch(`/api/espacios/${id}`, {
        method: 'DELETE'
        });
        if (!response.ok) throw new Error('Error al eliminar el espacio');
        mostrarAlerta('Espacio eliminado exitosamente');
        await mostrarEspacios();
        populateReservationSpaces(); // Actualiza el select de reservas
    } catch (error) {
        mostrarAlerta(error.message, 'error');
    }
}

/* Función para filtrar espacios (búsqueda y filtros) */
function filtrarEspacios() {
    const tipoFiltro = document.getElementById('filter-type').value;
    const disponibilidadFiltro = document.getElementById('filter-disponibility').value;
    const busqueda = document.getElementById('search-bar').value.toLowerCase();

    const lista = document.getElementById('resource-list');
    lista.innerHTML = '';

    const espaciosFiltrados = espaciosGlobal.filter(espacio => {
        const matchesTipo = (tipoFiltro === 'Tipo' || espacio.tipo === tipoFiltro);
        const matchesDisponibilidad = (disponibilidadFiltro === 'Disponibilidad' || espacio.disponibilidad === disponibilidadFiltro);
        const matchesBusqueda = espacio.nombre.toLowerCase().includes(busqueda);
        return matchesTipo && matchesDisponibilidad && matchesBusqueda;
    });

    espaciosFiltrados.forEach(espacio => {
        const fila = document.createElement('tr');
        fila.setAttribute('data-id', espacio.id);
        fila.innerHTML = `
        <td>${espacio.nombre}</td>
        <td>${espacio.tipo}</td>
        <td>${espacio.capacidad}</td>
        <td>${espacio.disponibilidad}</td>
        <td>
            <button class="btn btn-sm btn-warning" data-id="${espacio.id}" onclick="editarEspacio(this)">Editar</button>
            <button class="btn btn-sm btn-danger" data-id="${espacio.id}" onclick="eliminarEspacio(this)">Eliminar</button>
        </td>
        `;
        lista.appendChild(fila);
    });
}

document.getElementById('search-bar').addEventListener('input', filtrarEspacios);
document.getElementById('filter-type').addEventListener('change', filtrarEspacios);
document.getElementById('filter-disponibility').addEventListener('change', filtrarEspacios);

/* ========================================
Sección de RESERVAS
   ======================================== */

async function fetchReservations() {
    try {
        const response = await fetch('/api/reservas');
        if (!response.ok) throw new Error('Error al obtener las reservas');
        reservationsGlobal = await response.json();
        return reservationsGlobal;
    } catch (error) {
        mostrarAlerta('Error al cargar las reservas', 'error');
        return [];
    }
}

function getSpaceNameById(spaceId) {
    const espacio = espaciosGlobal.find(e => e.id == spaceId);
    return espacio ? espacio.nombre : 'N/A';
}

async function mostrarReservas() {
    await fetchReservations();
    const lista = document.getElementById('reservation-list');
    lista.innerHTML = '';

    reservationsGlobal.forEach(reserva => {
        const fila = document.createElement('tr');
        fila.setAttribute('data-id', reserva.id);
        const espacioNombre = getSpaceNameById(reserva.espacio);
        fila.innerHTML = `
        <td>${espacioNombre}</td>
        <td>${reserva.usuario}</td>
        <td>${reserva.fecha}</td>
        <td>${reserva.horaInicio}</td>
        <td>${reserva.horaFin}</td>
        <td>
            <button class="btn btn-sm btn-warning" data-id="${reserva.id}" onclick="editarReserva(this)">Editar</button>
            <button class="btn btn-sm btn-danger" data-id="${reserva.id}" onclick="eliminarReserva(this)">Eliminar</button>
        </td>
        `;
        lista.appendChild(fila);
    });
}

function populateReservationSpaces() {
    const select = document.getElementById('reservation-space');
    select.innerHTML = '';
    espaciosGlobal.forEach(espacio => {
        const option = document.createElement('option');
        option.value = espacio.id;
        option.textContent = espacio.nombre;
        select.appendChild(option);
    });
    }

    /* Envío del formulario de reservas: crear o actualizar */
    document.getElementById('reservation-form').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const id = document.getElementById('reservation-id').value;
    const espacio = document.getElementById('reservation-space').value;
    const usuario = document.getElementById('reservation-user').value;
    const fecha = document.getElementById('reservation-date').value;
    const horaInicio = document.getElementById('reservation-start').value;
    const horaFin = document.getElementById('reservation-end').value;
    
    if (!espacio || !usuario || !fecha || !horaInicio || !horaFin) {
        mostrarAlerta('Todos los campos de reserva deben completarse', 'error');
        return;
    }
    
    const reserva = { espacio, usuario, fecha, horaInicio, horaFin };
    
    try {
        let response;
        if (id) {
        // Actualización de reserva
        response = await fetch(`/api/reservas/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(reserva)
        });
        } else {
        // Creación de reserva
        response = await fetch('/api/reservas', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(reserva)
        });
        }
        if (!response.ok) throw new Error('Error al guardar la reserva');
        mostrarAlerta('Reserva guardada exitosamente');
        document.getElementById('reservation-form').reset();
        document.getElementById('reservation-id').value = '';
        mostrarReservas();
    } catch (error) {
        mostrarAlerta(error.message, 'error');
    }
});

/* Función para editar una reserva */
async function editarReserva(boton) {
    const id = boton.getAttribute('data-id');
    try {
        const response = await fetch(`/api/reservas/${id}`);
        if (!response.ok) throw new Error('Error al cargar la reserva');
        const reserva = await response.json();
        document.getElementById('reservation-id').value = reserva.id;
        document.getElementById('reservation-space').value = reserva.espacio;
        document.getElementById('reservation-user').value = reserva.usuario;
        document.getElementById('reservation-date').value = reserva.fecha;
        document.getElementById('reservation-start').value = reserva.horaInicio;
        document.getElementById('reservation-end').value = reserva.horaFin;
    } catch (error) {
        mostrarAlerta(error.message, 'error');
    }
}

/* Función para eliminar una reserva */
async function eliminarReserva(boton) {
    const id = boton.getAttribute('data-id');
    try {
        const response = await fetch(`/api/reservas/${id}`, {
        method: 'DELETE'
        });
        if (!response.ok) throw new Error('Error al eliminar la reserva');
        mostrarAlerta('Reserva eliminada exitosamente');
        mostrarReservas();
    } catch (error) {
        mostrarAlerta(error.message, 'error');
    }
}


document.addEventListener('DOMContentLoaded', async () => {
    await mostrarEspacios();
    populateReservationSpaces();
    mostrarReservas();
});
