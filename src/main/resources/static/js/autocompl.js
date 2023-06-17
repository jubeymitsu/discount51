// let ajxRequest = [
//     "+79216059950",
//     "+79216059951",
//     "+79216059952",
//     "+79216059953",
//     "+79216059954",
//     "+79216059955",
//     "+79216059956",
// ];

const resultBox = document.querySelector(".result-box");
const inputBox = document.getElementById("input-box");

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
        result.forEach(person => {
            switch (person.sale){
                case 'FIVE':
                    person.sale = "5"
                    break;
                case 'SEVEN':
                    person.sale = "7"
                    break;
                case 'TEN':
                    person.sale = "10"
                    break;
                case 'FIFTEEN':
                    person.sale = "15"
                    break;
                case 'TWENTY':
                    person.sale = "20"
                    break;
                default:
                    console.log('Unknown fruit');
            }
        })
    }
    display(result);
}

function display(result){
    const content = result.map(user => {
            return `<li id="${user.id}" onclick = selectInput(this)>` +
                '<b>Имя:</b> ' + user.firstName +
                '<b>Скидка:</b> ' + user.sale +
                '<b>Телефон: </b>' + user.phoneNumber +
                '</li>'
        }
    );
    resultBox.innerHTML = "<ul>" + content.join('') + "</ul>";
}

function display(result) {
    const content = result.map(user => {
            return `<li class="list-group-item"><a 
class="link-primary link-offset-2 link-underline link-underline-opacity-0" 
href="/users/page/${user.id}" id="${user.id}" onclick = selectInput(this)>` +
                '<b> Имя:</b> ' + user.firstName +
                '<b> Скидка: </b> ' + user.sale +
                '<b> Телефон: </b>' + user.phoneNumber +
                '</li></a>'
        }
    );
    resultBox.innerHTML = "<ul class='list-group'>" + content.join('') + "</ul>";
}

// function selectInput(element) {
//     // inputBox.value = list.innerHTML;
//     // resultBox.innerHTML = '';
//     let id = element.id
//     console.log(id)
//     window.location.href = `/users/page/${id}`;
// }

