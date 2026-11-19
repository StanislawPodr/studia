// Please see documentation at https://learn.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.
const canvas = document.querySelectorAll("canvas");
canvas.forEach((canva) => {
    canva.addEventListener("mousemove", drawLines(canva));
    canva.addEventListener("mouseout", resetCanva(canva));
})

function getMousePos(rect, event) {
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}

function resetCanva(canva) {
    return (event) => {
        const context = canva.getContext("2d");
        context.reset();
    }
}

function drawLines(canva) {
    return (event) => {
        const context = canva.getContext("2d");
        const rect = canva.getBoundingClientRect();
        const relativeMousePos = getMousePos(rect, event);

        context.reset();

        context.moveTo(0, 0);
        context.lineTo(relativeMousePos.x, relativeMousePos.y);
        context.moveTo(canva.width, 0);
        context.lineTo(relativeMousePos.x, relativeMousePos.y);
        context.moveTo(canva.width, canva.height);
        context.lineTo(relativeMousePos.x, relativeMousePos.y);
        context.moveTo(0, canva.height);
        context.lineTo(relativeMousePos.x, relativeMousePos.y);
        context.stroke();
    }
}


const MIN_VALUE = 0;
const MAX_VALUE = 99;

const form = document.getElementById("generator-form");
form.addEventListener("submit", createTableEvent);


function randInt() {
    return Math.floor(Math.random() * MAX_VALUE);
}

function createTableEvent(event) {
    // bez reload
    event.preventDefault();

    const table = document.getElementById("mult");
    const tableSize = document.getElementById("qty").valueAsNumber;
    const fragment = document.createDocumentFragment();
    console.log(tableSize);

    // usunięcie wcześniejszej tabeli
    table.replaceChildren();

    // stworzenie legendy (pierwszego wiersza)
    const legend = document.createElement("tr");
    const empty = document.createElement("th");
    legend.appendChild(empty);
    fragment.appendChild(legend);

    const multipliers = [];
    for (let newValIdx = 0; newValIdx < tableSize; newValIdx++) {

        const newRow = document.createElement("tr");
        const legendElement = document.createElement("th");

        // losowe wartości mnożników
        const newMult = randInt();
        multipliers[newValIdx] = newMult;

        legendElement.textContent = multipliers[newValIdx];

        // uzupełnienie legendy
        const fstRow = fragment.firstElementChild;
        fstRow.appendChild(legendElement);
        newRow.appendChild(legendElement.cloneNode(true));

        // uzupełenienie tabelki
        for (let multIdx = 0; multIdx <= newValIdx; multIdx++) {

            const newElement = document.createElement("td");
            const result = multipliers[multIdx] * newMult;

            newElement.textContent = result;
            newElement.classList.add(result % 2 ? "even" : "odd");

            // uzupełnienie ostatniego wiersza
            newRow.appendChild(newElement);
            /* dodanie nowej wartości na koniec każdego wiersza,
            gdy nie jest to ostatni wiersz (on ma być raz) */
            if (multIdx != newValIdx) {
                const row = fragment.children[multIdx + 1];
                row.appendChild(newElement.cloneNode(true));
            }
        }
        fragment.appendChild(newRow);
    }

    table.appendChild(fragment);
    form.reset();
}
