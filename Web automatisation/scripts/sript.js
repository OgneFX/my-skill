const buttons = document.querySelectorAll('.service-button');

buttons.forEach((button, index) => {
  button.addEventListener('click', (event) => {
    event.stopPropagation(); 
    buttons.forEach(btn => btn.classList.remove('expanded', 'bottom-right'));
    button.classList.add('expanded');
    if (index === 8) {
      button.classList.add('bottom-right');
    }
  });
});


document.addEventListener('click', (event) => {
  const isButton = event.target.closest('.service-button');
  if (!isButton) { 
    buttons.forEach(btn => btn.classList.remove('expanded', 'bottom-right'));
  }
});



document.addEventListener("DOMContentLoaded", () => {
  const buttons = document.querySelectorAll(".view-button");
  const image = document.querySelector(".main-image");
  const images = [
      "img/camera-tiandy.png",
      "img/oven-plk.png",
      "img/wirenboard-plk.png",
      "img/camera-tiandy4.png",
      "img/camera-tiandy5.png",
  ];

  let currentIndex = 0;
  let delay = 5000;
  let interval;

  const updateActiveButton = (index) => {
      buttons.forEach((btn, i) => {
          btn.classList.toggle("active", i === index);
      });
  };

  const updateImage = (index) => {
      image.src = images[index];
  };

  const startCycle = () => {
      interval = setInterval(() => {
          currentIndex = (currentIndex + 1) % buttons.length;
          updateActiveButton(currentIndex);
          updateImage(currentIndex);
      }, delay);
  };

 
  buttons.forEach((button, index) => {
      button.addEventListener("click", () => {
          clearInterval(interval);
          currentIndex = index;
          updateActiveButton(currentIndex);
          updateImage(currentIndex);
          delay = 10000; 
          startCycle();
      });
  });

  
  updateActiveButton(currentIndex);
  updateImage(currentIndex);
  startCycle();
});
