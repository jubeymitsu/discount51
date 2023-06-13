console.log("Hello verification")

const url = "/verification/verify"

async function verify() {

    let elem = document.getElementById("id");
    let id = parseInt(elem.getAttribute("value"));

    let input = document.getElementById("input-box").value;

    let sendData = {"id": id, "code": input};

    console.log(sendData);

    let status;

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(sendData)
    });
    const jsonData = await response.json();
    console.log(jsonData);

    const message = jsonData.message;
    console.log("STATUS: " + jsonData.message)
    if (message === "success")
        window.location.href = '/users';
    else
        console.log("WRONG!")
}


