new WOW().init();

let logoutBtn = document.getElementById('logout')

logoutBtn.addEventListener('click', function() {
    document.getElementById('logoutForm').submit();
    return false;
})
