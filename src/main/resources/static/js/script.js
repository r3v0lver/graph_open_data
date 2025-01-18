var data = {};

const JSONToFile = (obj, filename) => {
    event.preventDefault();
    event.stopPropagation();

    console.log("Preparing to download JSON file...");

    const blob = new Blob([JSON.stringify(obj, null, 2)], {
        type: 'application/json',
    });

    const url = URL.createObjectURL(blob);

    const a = document.createElement('a');
    a.href = url;
    a.download = `${filename}.json`;
    a.target = "_blank";

    setTimeout(() => {
        a.click();
        URL.revokeObjectURL(url);
    }, 100);

    console.log("JSON file download initiated.");
};

const CSVToFile = (obj, filename) => {
    try {
        const array = [
            ["id", "vnum", "adjmatrix", "simplegraph", "chromaticnumber", "isbipartite", "edgecount", "connectedcomponents", "density", "maxvertexdegree", "colorings"]
        ];

        obj.forEach(item => {
            const adjMatrixString = item.adjMatrix
                ? '"' + item.adjMatrix.map(row => `{${row.join(",")}}`).join(", ") + '"'
                : '""';

            const coloringsString = item.colorings
                ? '"' + item.colorings.map(c => `{${c.colorAssignment.join(",")}}`).join(", ") + '"'
                : '""';

            array.push([
                `graph_${item.id}`,
                item.vertexNumber,
                adjMatrixString,
                item.simpleGraph ? 't' : 'f',
                item.chromaticNumber,
                item.isBipartite ? 't' : 'f',
                item.edgeCount,
                item.connectedComponents,
                item.density,
                item.maxVertexDegree,
                coloringsString
            ]);
        });

        const csvContent = array.map(e => e.join(",")).join("\n");

        const blob = new Blob([csvContent], {
            type: 'text/csv',
        });

        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${filename}.csv`;
        a.target = "_blank";
        a.click();
        URL.revokeObjectURL(url);
        console.log('CSV file downloaded');
    } catch (error) {
        console.error('Error while creating CSV file:', error);
    }
};

async function fetchData(filter) {
    try {
        const response = await fetch("api/graph/getGraphsJson", {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            },
            body: JSON.stringify(filter)
        });

        if (!response.ok) {
            console.error("Failed to fetch data:", response.statusText);
            return;
        }

        data = await response.json();

        renderTable(data);
    } catch (error) {
        console.error("Error while fetching data:", error);
    }
}

function renderTable(data) {
    const tableBody = document.getElementById("graphTable");
    tableBody.innerHTML = '';

    data.forEach(graph => {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = graph.id || 'N/A';
        row.appendChild(idCell);

        const vertexNumberCell = document.createElement('td');
        vertexNumberCell.textContent = graph.vertexNumber || 'N/A';
        row.appendChild(vertexNumberCell);

        const adjMatrixCell = document.createElement('td');
        if (graph.adjMatrix) {
            adjMatrixCell.textContent = graph.adjMatrix.map(row => row.join(', ')).join(' | ');
        } else {
            adjMatrixCell.textContent = 'N/A';
        }
        row.appendChild(adjMatrixCell);

        const simpleGraphCell = document.createElement('td');
        simpleGraphCell.textContent = graph.simpleGraph ? 'Yes' : 'No';
        row.appendChild(simpleGraphCell);

        const chromaticNumberCell = document.createElement('td');
        chromaticNumberCell.textContent = graph.chromaticNumber || 'N/A';
        row.appendChild(chromaticNumberCell);

        const isBipartiteCell = document.createElement('td');
        isBipartiteCell.textContent = graph.isBipartite ? 'Yes' : 'No';
        row.appendChild(isBipartiteCell);

        const edgeCountCell = document.createElement('td');
        edgeCountCell.textContent = graph.edgeCount || 'N/A';
        row.appendChild(edgeCountCell);

        const connectedComponentsCell = document.createElement('td');
        connectedComponentsCell.textContent = graph.connectedComponents || 'N/A';
        row.appendChild(connectedComponentsCell);

        const densityCell = document.createElement('td');
        densityCell.textContent = graph.density || 'N/A';
        row.appendChild(densityCell);

        const maxVertexDegreeCell = document.createElement('td');
        maxVertexDegreeCell.textContent = graph.maxVertexDegree || 'N/A';
        row.appendChild(maxVertexDegreeCell);

        const coloringsCell = document.createElement('td');
        if (graph.colorings && graph.colorings.length > 0) {
            coloringsCell.textContent = graph.colorings
                .map(coloring => {
                    return coloring.colorAssignment.join(', ');
                })
                .join(' | ');
        } else {
            coloringsCell.textContent = 'N/A';
        }
        row.appendChild(coloringsCell);

        tableBody.appendChild(row);
    });
}

function setupFilterForm() {
    const form = document.getElementById('filterForm');
    var button = document.getElementById("submitButton");
    button.addEventListener('click', function(event) {
        event.preventDefault();
        var filter = {};
        if(document.getElementById('allAttrs').selected) {
            filter = {
                search: document.getElementById('searchText').value,
                searchId: true,
                searchVertexNumber: true,
                searchAdjMatrix: true,
                searchSimpleGraph: true,
                searchChromaticNumber: true,
                searchIsBipartite: true,
                searchEdgeCount: true,
                searchConnectedComponents: true,
                searchDensity: true,
                searchMaxVertexDegree: true
            }
        } else {
            filter = {
                search: document.getElementById('searchText').value,
                searchId: document.getElementById('id').selected,
                searchVertexNumber: document.getElementById('vertexNumber').selected,
                searchAdjMatrix: document.getElementById('adjMatrix').selected,
                searchSimpleGraph: document.getElementById('simpleGraph').selected,
                searchChromaticNumber: document.getElementById('chromaticNumber').selected,
                searchIsBipartite: document.getElementById('isBipartite').selected,
                searchEdgeCount: document.getElementById('edgeCount').selected,
                searchConnectedComponents: document.getElementById('connectedComponents').selected,
                searchDensity: document.getElementById('density').selected,
                searchMaxVertexDegree: document.getElementById('maxVertexDegree').selected
            };
        }
        fetchData(filter);
    });

    document.getElementById('downloadJson').addEventListener('click', function() {
        event.preventDefault();
        JSONToFile(data, 'filtered_data');
    });

    document.getElementById('downloadCsv').addEventListener('click', function() {
        event.preventDefault();
        CSVToFile(data, 'filtered_data');
    });
}

window.onload = function() {
    setupFilterForm();
    fetchData({});
};