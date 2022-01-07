function deRequire(elClass) {
    let el=document.getElementsByClassName(elClass);

    let atLeastOneChecked=false;//at least one cb is checked
    for (i=0; i<el.length; i++) {
        if (el[i].checked === true) {
            atLeastOneChecked=true;
        }
    }

    if (atLeastOneChecked === true) {
        for (i=0; i<el.length; i++) {
            el[i].required = false;
        }
    } else {
        for (i=0; i<el.length; i++) {
            el[i].required = true;
        }
    }
}
let x = 0;
function addInput() {
    let str = '<input type="text" placeholder="Автор" pattern="^[а-яА-ЯёЁa-zA-Z\s]+$" name="author"/><input type="file" name="authorImage"/><div id="input' + (x + 1) + '"/>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}
