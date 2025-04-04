const img = document.getElementById('bouncing-image');

// variables for position and speed
let posX, posY;
let speedX = 2;
let speedY = 2;

// variables for img size
let imgWidth, imgHeight;

// variable for rotation angle
let angle = 0;

function animate() {
    // update pos
    posX += speedX;
    posY += speedY;

    // check if Fada meats border
    if (posX + imgWidth >= window.innerWidth || posX <= 0) {
        speedX = -speedX;
    }
    if (posY + imgHeight >= window.innerHeight || posY <= 0) {
        speedY = -speedY;
    }

    // update rotation angle
    angle += 2;
    img.style.transform = `rotate(${angle}deg)`;

    // move img
    img.style.left = posX + 'px';
    img.style.top = posY + 'px';

    // make it animation
    requestAnimationFrame(animate);
}

function init() {
    // get img params
    imgWidth = img.offsetWidth;
    imgHeight = img.offsetHeight;

    // init start pos
    posX = Math.random() * (window.innerWidth - imgWidth);
    posY = Math.random() * (window.innerHeight - imgHeight);

    // set random start direction to move
    if (Math.random() < 0.5) speedX = -speedX;
    if (Math.random() < 0.5) speedY = -speedY;

    // set start pos
    img.style.left = posX + 'px';
    img.style.top = posY + 'px';

    // start animation
    animate();
}

// check if img installed
if (img.complete) {
    init();
} else {
    img.onload = init;
}

// // update img size when user resizes window
// window.addEventListener('resize', () => {
//     imgWidth = img.offsetWidth;
//     imgHeight = img.offsetHeight;
// });
