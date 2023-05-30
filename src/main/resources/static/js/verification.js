console.log("Hello verification")

const url = "/verify"

function verify() {

    let elem = document.getElementById("id");
    let id = parseInt(elem.getAttribute("value"));

    let input = document.getElementById("input-box").value;

    let data = {"id": id, "code": input};

    console.log(data);

    let status;

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(result => {
            console.log(result);
            status = result.message
            console.log(status)
        })
    console.log("STATUS: " + status)
    if (status == "success")
        window.location.href = '/users';
    else
        console.log("WRONG!")

}


