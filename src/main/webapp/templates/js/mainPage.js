var xhr = new XMLHttpRequest();
xhr.open('GET',"https://jsonplaceholder.typicode.com/albums",true);
xhr.send();

var udata;

xhr.onload = function()
{
    if(this.status == 200)
    {
        var userdata = JSON.parse(this.responseText);
        console.log(userdata);
        udata = userdata;
        data(1);
    }
}

$("li").click(function ()
{
    var a = $(this).attr("value");
    console.log("value li "+ a);
    data(a);
});

function data(a)
{
    var output = "";
    for(i=((a-1)*10);i<(a*10);i++)
    {
        output +='<tr>'+
            '<td>'+ udata[i].userId + '</td>'+
            '<td>'+ udata[i].id + '</td>'+
            '<td>'+ udata[i].title + '</td>'+ '<br>'
        '</tr>';
    }
    document.getElementById('user').innerHTML = output;
}