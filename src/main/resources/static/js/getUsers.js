// Call the dataTables jQuery plugin
$(document).ready(function() {
  getUsersRest();
  $('#getUsers').DataTable();

  userLogin();
});

function userLogin(){
  document.getElementById("txtEmailUser").outerHTML = localStorage.email;
}

async function getUsersRest(){

  const request = await fetch("api/users", {
    method: "GET",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Autentification': localStorage.token
    }
  });

  const users = await request.json();

  let listHtml = '';
  for (usuario of users) {
    let bottom = '<a href="#" onclick="deleteUser('+ usuario.id + ')" class="btn btn-danger btclassNamecle btn-sm"> <i class="fas fa-trash"></iclassName>'

    let phoneTxt = usuario.phone == null ? '-' : usuario.phone;

    let userHtml = '<tr><td>'
        +usuario.id+'</td><td>'
        +usuario.name+ ' '
        +usuario.lastName+'</td><td>'
        +usuario.email+'</td><td>'
        +phoneTxt+'</td><td>'
        +usuario.password
        +'</td><td>'
        + bottom
        +'</td> </tr>';
    listHtml += userHtml;
  }

  document.querySelector("#getUsers tbody").outerHTML = listHtml;
}

async function deleteUser(id){

  if(!confirm("¿Desea eliminar este usuario?")){
    return;
  }

  const request = await fetch("api/users/"+id,{
    method: "DELETE",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'Application/json',
      'Autentification': localStorage.token
    }
  });

  location.reload();
}