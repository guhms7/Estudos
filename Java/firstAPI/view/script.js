const url = "http://localhost:8080/task/user/5";

function hideloader() {
    document.getElementById("loadding").style.display = "none";
}

function show(task) {
    console.log('Data received:', tasks);
    let tab = `
            <thead>
                <th scope="col">Task id</th>
                <th scope="col">Username</th>
                <th scope="col">User id</th>
                <th scope="col">Description</th>
            </thead>`;


    for (let t of task) {
        console.log('Task:', task);
        tab += `
            <tr>
                <td scope="row">${t.id}</td>
                <td>${t.user.username}</td>
                <td>${t.user.id}</td>
                <td>${t.description}</td>
            </tr>`;
    }
    document.getElementById("tasks").innerHTML = tab;
}

async function getAPI(url) {
    const response = await fetch(url, { method: "GET" });
    var data = await response.json();
    console.log(data);
    if (response) hideloader();
    show(data);
}

getAPI(url);