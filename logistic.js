console.log("Script loaded");

function showSection(sectionId) {
  const sections = [
    "home",
    "register",
    "login",
    "dashboard",
    "bookRidePage",
    "bookDispatcherPage",
    "becomeDriverPage",
    "becomeRiderPage",
  ];
  sections.forEach((id) => {
    document.getElementById(id).style.display =
      id === sectionId ? "block" : "none";
  });
  if (sectionId !== "register") {
    const registerForm = document.getElementById("register-form");
    if (registerForm) registerForm.reset();
    const regMessage = document.getElementById("regMessage");
    if (regMessage) regMessage.textContent = "";
  }
  if (sectionId !== "login") {
    const loginForm = document.getElementById("login-form");
    if (loginForm) loginForm.reset();
    const loginMessage = document.getElementById("loginMessage");
    if (loginMessage) loginMessage.textContent = "";
  }
}

// Simulate form handling
const registerForm = document.getElementById("register-form");
const loginForm = document.getElementById("login-form");

if (registerForm) {
  registerForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const firstName = document.getElementById("first-name").value;
    const lastName = document.getElementById("last-name").value;
    const email = document.getElementById("email").value;
    const phoneNumber = document.getElementById("phone").value;
    const registerPassword = document.getElementById("password").value;
    const homeAddress = document.getElementById("home-address").value;

    fetch("http://localhost:8080/api/logistics/user/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
        password: registerPassword,
        homeAddress: homeAddress,
      }),
    })
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        const reponseMessage = document.getElementById("regMessage");
        if (response.ok) {
          reponseMessage.textContent = "Registration successful!";
          reponseMessage.style.color = "green";

          showSection("login");
        } else {
          reponseMessage.textContent =
            data.data || "Registration failed. Please try again.";
          reponseMessage.style.color = "red";
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        const responseMessage = document.getElementById("regMessage");
        responseMessage.textContent = "An error occurred during registration.";
        responseMessage.style.color = "red";
      });
  });
}

if (loginForm) {
  loginForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const loginPhoneNumber = document.getElementById("login-phone").value;
    const loginPassword = document.getElementById("login-password").value;

    fetch("http://localhost:8080/api/logistics/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        phoneNumber: loginPhoneNumber,
        password: loginPassword,
      }),
    })
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        const reponseMessage = document.getElementById("loginMessage");
        if (response.ok) {
          reponseMessage.textContent = "Login successful!";
          reponseMessage.style.color = "green";
          localStorage.setItem("flashUser", JSON.stringify(data));
          showSection("dashboard");
        } else {
          reponseMessage.textContent =
            data.data || "Login failed. Please try again.";
          reponseMessage.style.color = "red";
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        const responseMessage = document.getElementById("loginMessage");
        responseMessage.textContent = "An error occurred during login.";
        responseMessage.style.color = "red";
      });
  });
}

function callLogout() {
  showSection("home");
}

function callSetOperativeActive() {
  alert("Operative set as active.");
}

function callBookRide() {
  showSection("bookRidePage");
}

function callBookDispatcher() {
  showSection("bookDispatcherPage");
}

function callBecomeRider() {
  showSection("becomeRiderPage");
}

function callBecomeDriver() {
  showSection("becomeDriverPage");
}
