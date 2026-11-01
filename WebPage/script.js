// 1. Data: a list of drinks
const drinks = [
  { name: "Espresso", description: "Strong and simple.",      price: 10 },
  { name: "Latte",    description: "Smooth with milk.",       price: 16 },
  { name: "Mocha",    description: "Coffee meets chocolate.", price: 18 },
  { name: "Iced Tea", description: "Cold and refreshing.",    price: 12 }
];

// 2. Function: build the menu cards
function renderMenu() {
  const container = document.querySelector("#menu-cards");

  drinks.forEach(drink => {
    const card = document.createElement("article");
    card.classList.add("card");

    card.innerHTML = `
      <h3>${drink.name}</h3>
      <p>${drink.description}</p>
      <p><strong>${drink.price} NIS</strong></p>
    `;

    container.appendChild(card);
  });
}

renderMenu();

// 3. Form: check the email when the user clicks Subscribe
const form = document.querySelector("#contact-form");
const emailInput = document.querySelector("#email");
const message = document.querySelector("#form-message");

form.addEventListener("submit", (event) => {
  event.preventDefault();   // stop the page from reloading

  const email = emailInput.value.trim();

  if (email === "" || !email.includes("@")) {
    message.textContent = "Please enter a valid email.";
    message.className = "error";
  } else {
    message.textContent = "Thank you! You are subscribed.";
    message.className = "success";
    form.reset();
  }
});