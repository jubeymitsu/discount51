let ajxRequest = [
    "+79216059950",
    "+79216059951",
    "+79216059952",
    "+79216059953",
    "+79216059954",
    "+79216059955",
    "+79216059956",
];

const resultBox = document.querySelector(".result-box");
const inputBox = document.getElementById("input-box");

inputBox.onkeyup = function (){
    let result = [];
    let input = inputBox.value;
    if (input.length){
        result = ajxRequest.filter(keyword => {
            return keyword.toLowerCase().includes(input.toLowerCase());
        });
        console.log(result)
    }
    display(result);
}

function display(result){
      const content = result.map(list =>{
          return "<li onclick=selectInput(this)>" + list + "</li>"
          }
      );
      resultBox.innerHTML = "<ul>" + content.join('') + "</ul>";
}

function selectInput(list){
    inputBox.value = list.innerHTML;
    resultBox.innerHTML = '';
}

