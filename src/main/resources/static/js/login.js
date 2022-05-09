async function login(){

    let datos = {};

    datos.email = document.getElementById("txtName").value;
    datos.password = document.getElementById("txtPassword").value;

    const request = await fetch("api/login", {
       method: "POST",
       headers: {
           'Accept': 'Application/json',
           'Content-Type': 'Application/json'
       },
        body: JSON.stringify(datos)
    });

    const response = await request.text();
    if(response != "FAIL"){
        localStorage.token = response;
        localStorage.email = datos.email;
        window.location.href = "users.html";
    } else {
        alert("Datos incorrectos, por favor intente de nuevo.");
    }

}