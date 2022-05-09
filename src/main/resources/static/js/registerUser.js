




async function registerUser(){

    let datos = {};
    datos.name = document.getElementById('txtName').value;
    datos.lastName = document.getElementById('txtLastName').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    datos.passwordRepeat = document.getElementById('txtPasswordRepeat').value;

    if(datos.password != datos.passwordRepeat){
        alert("Las contraseñas son diferentes.")
        return;
    }

    const request = await fetch("api/users",  {
        method: "POST",
        headers: {
            'Accept': 'Application/json',
            'Content-Type': 'Application/json'
        },
        body: JSON.stringify(datos)
    })

    alert("Usuario registrado correctamente.");
    window.location.href = "login.html";
}