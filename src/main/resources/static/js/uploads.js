document.addEventListener("DOMContentLoaded", function(event) {

    document.getElementById("Index").onclick = function (){
        document.cookie = "sort=index; path=/;";
        changeSortOrder();
    };

    document.getElementById("FirstFile").onclick = function (){
        document.cookie = "sort=firstFile; path=/;";
        changeSortOrder();
    };

    document.getElementById("SecondFile").onclick = function (){
        document.cookie = "sort=secondFile; path=/;";
        changeSortOrder();
    };

    document.getElementById("Score").onclick = function (){
        document.cookie = "sort=score; path=/;";
        changeSortOrder();
    };

    function changeSortOrder(){
        if (document.cookie.includes("desc")){
            document.cookie = "orderBy=asc; path=/;";
        } else {
            document.cookie = "orderBy=desc; path=/;";
        }
        window.location.reload();
    }
});