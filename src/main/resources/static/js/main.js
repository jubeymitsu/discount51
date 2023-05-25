function showConfirmationAndDelete(id) {
    console.log(Id)
    if (confirm('Вы точно хотите удалить этого пользователя?')) {
        window.location.href = `/users/delete/${id}`;
    }
}
