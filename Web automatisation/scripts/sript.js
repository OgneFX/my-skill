document.querySelectorAll('.service-button').forEach((button, index) => {
  button.addEventListener('click', () => {
    
    document.querySelectorAll('.service-button').forEach(btn => btn.classList.remove('expanded', 'bottom-right'));

    
    button.classList.add('expanded');

    
    if (index === 8) { 
      button.classList.add('bottom-right');
    }
  });
});