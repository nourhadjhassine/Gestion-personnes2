const apiUrl = "http://localhost:8080/projet/api/users";
const form = document.getElementById("personForm");
const formTitle = document.getElementById("formTitle");

function loadPersons() {
    fetch(`${apiUrl}/affiche`)
        .then(res => res.json())
        .then(data => displayPersons(data))
        .catch(err => console.error(err));
}




function displayPersons(persons) {
    const tbody = document.querySelector("#personTable tbody");
    tbody.innerHTML = "";

    persons.forEach(p => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.age}</td>
            <td>
                <button onclick="editPerson(${p.id}, '${p.name}', ${p.age})">Modifier</button>
                <button onclick="deletePerson(${p.id})">Supprimer</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

form.addEventListener("submit", function(e) {
    e.preventDefault();

    const id = Number(document.getElementById("personId").value);
    const name = document.getElementById("name").value;
    const age = Number(document.getElementById("age").value);

    if (id) {
      
        fetch(`${apiUrl}/update/${id}/${age}/${name}`, { method: "PUT" })
            .then(res => res.json())
            .then(data => {
                loadPersons();
                resetForm();
            });
    } else {
      
        fetch(`${apiUrl}/add/${age}/${name}`, { method: "PUT" })
            .then(res => res.json())
            .then(data => {
                loadPersons();
                resetForm();
            });
    }
});






// Pré-remplir formulaire pour modification
function editPerson(id, name, age) {
    document.getElementById("personId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("age").value = age;
    formTitle.innerText = "Modifier une personne";
}




// Réinitialiser formulaire
function resetForm() {
    document.getElementById("personId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("age").value = "";
    formTitle.innerText = "Ajouter une personne";
}



// Supprimer une personne
function deletePerson(id) {
    if (!confirm("Voulez-vous vraiment supprimer cette personne ?")) return;

    fetch(`${apiUrl}/remove/${id}`, { method: "DELETE" })
        .then(res => res.json())
        .then(data => loadPersons());
}

// Recherche par nom ou ID
function searchPerson() {
    const value = document.getElementById("searchInput").value.toLowerCase();
    fetch(`${apiUrl}/affiche`)
        .then(res => res.json())
        .then(data => {
            const filtered = data.filter(p =>
                p.name.toLowerCase().includes(value) || p.id == value
            );
            displayPersons(filtered);
        });
}

// Chargement initial
loadPersons();
