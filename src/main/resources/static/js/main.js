function showConfirmationAndDelete(event) {
    console.log(event.target)
    console.log(event.target.id)

    let id = event.target.id.replace("d", "");
    console.log(id)
    if (confirm('Вы точно хотите удалить этого пользователя?')) {
        window.location.href = `/users/delete/${id}`;
    }
}
