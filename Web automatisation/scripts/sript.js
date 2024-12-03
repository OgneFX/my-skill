const buttons = document.querySelectorAll('.grid-item');

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
  const isButton = event.target.closest('.grid-item');
  if (!isButton) { 
    buttons.forEach(btn => btn.classList.remove('expanded', 'bottom-right'));
  }
});

// Header анимация

document.addEventListener("DOMContentLoaded", () => {
  const buttons = document.querySelectorAll(".view-button");
  const image = document.querySelector(".main-image");
  const textElement = document.querySelector(".b-text");
  const formImage = document.querySelector(".form-section-img");

 
  const images = [
      "img/header/camera-tiandy2.png",
      "img/header/fire-protect.png",
      "img/header/datchyky.png",
      "img/header/skud.png",
      "img/header/wirenboard-plk7.png",
      "img/header/smart-house.png",
      "img/header/modem.png",
      "img/header/apc-ups.png",
      "img/header/electric.png",
  ];

    const formImages = [
    "img/header/electric.png",       
    "img/header/apc-ups.png",  
    "img/header/modem.png",      
    "img/header/smart-house.png",          
    "img/header/skud.png",    
    "img/header/skud-bolid.png",  
    "img/header/camera-tiandy2.png",         
    "img/header/fire-protect.png",         
    "img/header/wirenboard-plk7.png",   
  ];

  const texts = [
    "Проектирование, установка и обслуживание систем видеонаблюдения для дома, офиса и бизнеса.",
    "Монтаж и настройка систем пожарной безопасности и автоматического оповещения.",
    "Комплексные решения для защиты объектов: сигнализация, датчики движения и контроль доступа.",
    "Управление доступом на объекты с помощью карт, кодов или биометрии.",
    "Проектирование и установка систем автоматизации для контроля инженерных процессов.",
    "Создание интеллектуальных систем управления освещением, климатом и безопасностью вашего дома.",
    "Настройка беспроводных сетей и сетевой инфраструктуры для стабильного и быстрого соединения.",
    "Установка и обслуживание ИБП для защиты техники от перебоев с электроэнергией.",
    "Профессиональный монтаж и обслуживание электрических сетей и оборудования.",
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
      formImage.src = formImages[index];
      textElement.textContent = texts[index];
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
