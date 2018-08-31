$('#FormId').on('submit', 'form', bookavecID())


function bookavecID(){
    $.ajax({
        url: "http://localhost:8080/Java/rest/hello/book/"+$('#Bookid').val()),
        type: 'GET',
        data: '',
        dataType: '.html',
    }),
 })