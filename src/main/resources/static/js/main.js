/*=============== SHOW MENU ===============*/
const navMenu = document.getElementById('nav-menu'),
      navToggle = document.getElementById('nav-toggle'),
      navClose = document.getElementById('nav-close')

/* Menu show */
navToggle.addEventListener('click', () =>{
   navMenu.classList.add('show-menu')
})

/* Menu hidden */
navClose.addEventListener('click', () =>{
   navMenu.classList.remove('show-menu')
})

/*=============== SEARCH ===============*/
const search = document.getElementById('search'),
      searchBtn = document.getElementById('search-btn'),
      searchClose = document.getElementById('search-close')

/* Search show */
searchBtn.addEventListener('click', () =>{
   search.classList.add('show-search')
})

/* Search hidden */
searchClose.addEventListener('click', () =>{
   search.classList.remove('show-search')
})

/*=============== LOGIN ===============*/
const login = document.getElementById('login'),
      loginBtn = document.getElementById('login-btn'),
      loginClose = document.getElementById('login-close')

/* Login show */
loginBtn.addEventListener('click', () =>{
   login.classList.add('show-login')
})

/* Login hidden */
loginClose.addEventListener('click', () =>{
   login.classList.remove('show-login')
})

/*=============== VENTANA MODAL (EXITO) ===============*/
const btnAbrirPopup = document.getElementById('btn-abrir-popup'),
    overlay = document.getElementById('overlay'),
    popup = document.getElementById('popup'),
    btnCerrarPopup = document.getElementById('btn-cerrar-popup');

    btnAbrirPopup.addEventListener('click', () =>{
      overlay.classList.add('active');
      popup.classList.add('active');
   });
   
   btnCerrarPopup.addEventListener('click', () =>{
      overlay.classList.remove('active');
      popup.classList.remove('active');
   });

   /*=============== VENTANA MODAL (ERROR) ===============*/
const btnAbrirPopupError = document.getElementById('btn-abrir-popup-error'),
overlayError = document.getElementById('overlay-error'),
popupError = document.getElementById('popup-error'),
btnCerrarPopupError = document.getElementById('btn-cerrar-popup-error');

btnAbrirPopupError.addEventListener('click', () =>{
  overlayError.classList.add('active');
  popupError.classList.add('active');
});

btnCerrarPopupError.addEventListener('click', () =>{
  overlayError.classList.remove('active');
  popupError.classList.remove('active');
});