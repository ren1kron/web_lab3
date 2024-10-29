'use strict';

const params = {
    x: 0,
    y: 0,
    r: 0
};


/**
 * Center of x axis on svg graph. Equals half of svg.width
 * @type {number}
 */
const centerX = document.getElementById("svg").getAttribute('width') / 2; // Центр по оси X
/**
 * Center of y axis on svg graph. Equals half of svg.height
 * @type {number}
 */
const centerY = document.getElementById("svg").getAttribute('height') / 2; // Центр по оси Y
/**
 * This const sets a scale for svg elements
 * @type {number}
 */
const scale = 40;

/**
 * Handles checking any of 'x' checkboxes
 * @param self
 */
function checkX(self) {
    document
        .querySelectorAll('input[type="checkbox"][name="x"]')
        .forEach((checkbox) => (checkbox.checked = false));
    self.checked = true;
    params.x = self.value;
    updatePointer();
}

/**
 * Handles changes in 'y' text form, validates input
 * @param ev – change in 'y' text form
 */
function checkY(ev) {
    const input = ev.target.value; /* string value of y form input */
    const error = document.getElementById('errorY');

    /* validate y */
    if (isNaN(input) || parseFloat(input) < -5 || parseFloat(input) > 5) {
        error.innerText = 'Y must be in [-5;5]';
        error.hidden = false;
        throw new Error('Invalid param Y');
    }
    params.y = parseFloat(input);
    error.hidden = true;
    updatePointer();
}
document.getElementById('y').addEventListener('change', (ev) => checkY(ev));

/**
 * Handles checking any of 'r' checkboxes
 * @param self
 */
function checkR(self) {
    document
        .querySelectorAll('input[type="checkbox"][name="r"]')
        .forEach((checkbox) => (checkbox.checked = false));
    self.checked = true;
    params.r = parseFloat(self.value);
    updateShapes();
}


/**
 *
 */
function svgClick(event) {
    /* check if radius set */
    if (params.r <= 0) {
        alert('Please, set a radius first');
        return;
    }

    const svg = document.getElementById('svg');
    const rect = svg.getBoundingClientRect();
    const xClick = event.clientX - rect.left;
    const yClick = event.clientY - rect.top;

    params.x = (xClick - centerX) / scale;
    params.y = (centerY - yClick) / scale;
    updatePointer();

    console.log('param x:' + params.x);
    console.log('param y:' + params.y);
    console.log('param r:' + params.r);
    sendPoint(params.x, params.y);
}
document.getElementById('svg').addEventListener('click', svgClick);

function sendPoint(x, y) {
    const form = document.getElementById('data-form');

    const customX = document.getElementById('custom-x');
    customX.value = x.toString();
    customX.disabled = false;
    checkX(customX);

    console.log(y);
    console.log(params.r);

    form["y"].value = y;
    form["r"].value = params.r;

    console.log('form value x: ' + form["x"].value);
    console.log('form value y: ' + form["y"].value);
    console.log('form value r: ' + form["r"].value);
    form.submit();
}

// document.getElementById('data-form')
//     .addEventListener('submit', function(ev) {
//
//     });



/**
 * Updates pointer on graph when user enters cords using input form
 */
function updatePointer() {
    // Вычисляем положение точки относительно центра графика
    // Масштабируем координаты
    const scaledX = centerX + params.x * scale;
    const scaledY = centerY - params.y * scale; // Отрицательное, чтобы двигать вверх

    // Получаем элемент circle для отрисовки точки
    let pointElement = document.getElementById('pointer');

    // Устанавливаем новые координаты для точки
    pointElement.setAttribute('cx', scaledX);
    pointElement.setAttribute('cy', scaledY);
}
function updateShapes() {
    /* get radius */
    const radius = params.r * scale;

    /* update triangle */
    const trianglePoints = `
    ${centerX},${centerY}
    ${centerX},${centerY + radius}
    ${centerX - radius/2},${centerY}
  `;
    document.getElementById('triangle').setAttribute('points', trianglePoints.trim());

    /* update rectangle */
    const rectangle = document.getElementById('rectangle');
    rectangle.setAttribute('x', centerX);
    rectangle.setAttribute('y', centerY - radius);
    rectangle.setAttribute('width', radius/2);
    rectangle.setAttribute('height', radius);

    /* update quarter circle */
    const quarterCirclePath = `
    M ${centerX} ${centerY}
    L ${centerX - radius/2} ${centerY}
    A ${radius/2} ${radius/2} 0 0 1 ${centerX} ${centerY - radius/2}
    Z `;
    /*
     M – move to (set starting point)
     L – line to
     A – arc to (rx ry x-axis-rotation large-arc-flag(0 for small and 1 for large) sweep-flag x y)
    */
    document.getElementById('quarterCircle').setAttribute('d', quarterCirclePath.trim());
}

/**
 * Validates params (for sending by clicking button)
 * @returns {boolean}
 */
function validateState() {

    if (isNaN(params.x) || params.x < -2 || params.x > 2) {
        return false;
    }

    if (isNaN(params.y) || params.y < -5 || params.y > 5) {
        return false;
    }

    if (isNaN(params.r) || params.r <= 0 || params.r > 3) {
        return false;
    }
    return true;
}
document.getElementById('data-form').addEventListener('submit', function (ev) {
    if (!validateState()) {
        ev.preventDefault();
        alert('Sorry, you are not able to submit your variables. Some of them are invalid.');
    }
});

document.querySelectorAll('#result-table tbody tr').forEach(function(row) {
    row.addEventListener('click', function() {
        const x = this.dataset.x;
        const y = this.dataset.y;
        const r = this.dataset.r;

        window.location.href = `${contextPath}/controller?x=${encodeURIComponent(x)}&y=${encodeURIComponent(y)}&r=${encodeURIComponent(r)}`;
    });
});

