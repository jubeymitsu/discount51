// let ajxRequest = [
//     "+79216059950",
//     "+79216059951",
//     "+79216059952",
//     "+79216059953",
//     "+79216059954",
//     "+79216059955",
//     "+79216059956",
// ];

async function postData(data) {
    let formData = new FormData();
    formData.append('phoneNumber', data);

    const options = {
        method: 'POST',
        body: formData,
    };

    let response = await fetch('/phoneSearch', options);

    const result = await response.json();
    console.log(result);
    return result;
}

const resultBox = document.querySelector(".result-box");
const inputBox = document.getElementById("input-box");

inputBox.onkeyup = async function () {
    let result = [];
    let input = inputBox.value;
    if (input.length) {
        // result = ajxRequest.filter(keyword => {
        //     return keyword.toLowerCase().includes(input.toLowerCase());
        // });
        // console.log(result)
        result = await postData(input.toLowerCase())
        console.log(result)
    }
    display(result);
}

function display(result){
    const content = result.map(user => {
            return `<li id="${user.id}" onclick = selectInput(this)>` +
                '<b>Name:</b> ' + user.firstName +
                '<b>Sale:</b> ' + user.sale +
                '<b>Phone number: </b>' + user.phoneNumber +
                '</li>'
        }
    );
    resultBox.innerHTML = "<ul>" + content.join('') + "</ul>";
}

function selectInput(element) {
    // inputBox.value = list.innerHTML;
    // resultBox.innerHTML = '';
    let id = element.id
    console.log(id)
    window.location.href = `/users/page/${id}`;
}

