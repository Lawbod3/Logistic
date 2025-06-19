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
    "operationsPage",
  ];
  sections.forEach((id) => {
    document.getElementById(id).style.display =
      id === sectionId ? "block" : "none";
  });
  if (sectionId === "dashboard") {
    const user = JSON.parse(localStorage.getItem("flashUser"));
    const notificationBtn = document.getElementById("notification-btn");
    if (
      notificationBtn &&
      user &&
      ["DRIVER", "DISPATCHER"].includes(user.data.userType.toUpperCase())
    ) {
      notificationBtn.style.display = "block";
      updateNotificationButton();
    } else if (notificationBtn) {
      notificationBtn.style.display = "none";
    }
  }
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
function callLogout() {
  showSection("home");
}

function callSetOperativeActive() {
  const user = JSON.parse(localStorage.getItem("flashUser"));
  const question = document.getElementById("availability-question");
  const responseMessage = document.getElementById("availabilityResponse");

  const userType = user.data.userType?.toUpperCase();

  if (!user.data || !user.data.userType) {
    alert("Only drivers or dispatchers can access this feature.");
    showSection("dashboard");
    return;
  }

  if (userType === "DRIVER") {
    question.textContent = "Do you want to turn on Driver mode to available?";
  } else if (userType === "DISPATCHER") {
    question.textContent =
      "Do you want to turn on Dispatcher mode to available?";
  } else {
    alert("Only drivers or dispatchers can access this feature.");
    showSection("dashboard");
    return;
  }

  responseMessage.textContent = "";
  responseMessage.style.color = "black";

  showSection("operationsPage");
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

const registerForm = document.getElementById("register-form");
const loginForm = document.getElementById("login-form");
const driverForm = document.getElementById("become-driver-form");
const riderForm = document.getElementById("become-rider-form");
const bookRide = document.getElementById("book-ride-form");
const bookDispatcher = document.getElementById("book-dispatcher-form");
const confirmAvailabilityYes = document.getElementById("confirmYes");
const confirmAvailabilityNo = document.getElementById("confirmNo");

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
          localStorage.setItem("flashUser", JSON.stringify(data));
          showSection("dashboard");
          if (
            ["DRIVER", "DISPATCHER"].includes(data.data.userType.toUpperCase())
          ) {
            checkNotification();
            updateNotificationButton();
          }
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

if (driverForm) {
  driverForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const user = JSON.parse(localStorage.getItem("flashUser"));
    const userId = user.data.id;

    const userType = user.data.userType?.toUpperCase();

    if (userType === "DRIVER" || userType === "RIDER") {
      alert("You are already a driver!");
      showSection("dashboard");
      return;
    }

    const driversLicense = document.getElementById("drivers-license").value;
    const vehicleId = document.getElementById("vehicle-id").value;
    const vehicleDescription = document.getElementById(
      "vehicle-description"
    ).value;

    fetch(
      "http://localhost:8080/api/logistics/user/login/driver-registration",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          driversLicenseNumber: driversLicense,
          userId: userId,
          vehicleId: vehicleId,
          vehicleDescription: vehicleDescription,
        }),
      }
    )
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        const reponseMessage = document.getElementById("driverMessage");
        if (response.ok) {
          reponseMessage.textContent = "Driver registration successful!";
          reponseMessage.style.color = "green";
          showSection("login");
        } else {
          reponseMessage.textContent =
            data.data || "Driver registration failed. Please try again.";
          reponseMessage.style.color = "red";
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        const responseMessage = document.getElementById("driverMessage");
        responseMessage.textContent =
          "An error occurred during driver registration.";
        responseMessage.style.color = "red";
      });
  });
}

if (riderForm) {
  riderForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const user = JSON.parse(localStorage.getItem("flashUser"));
    const userId = user.data.id;

    const userType = user.data.userType?.toUpperCase();

    if (userType === "RIDER" || userType === "DRIVER") {
      alert("You are already a rider!");
      showSection("dashboard");
      return;
    }

    const ridersLicense = document.getElementById("riders-license").value;
    const motorcycleId = document.getElementById("motorcycle-id").value;
    const motorcycleDescription = document.getElementById(
      "motorcycle-description"
    ).value;

    fetch(
      "http://localhost:8080/api/logistics/user/login/dispatcher-registration",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ridersLicenseNumber: ridersLicense,
          userId: userId,
          motorcycleId: motorcycleId,
          motorcycleDescription: motorcycleDescription,
        }),
      }
    )
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        const reponseMessage = document.getElementById("riderMessage");
        if (response.ok) {
          reponseMessage.textContent = "Dispatcher registration successful!";
          reponseMessage.style.color = "green";
          showSection("login");
        } else {
          reponseMessage.textContent =
            data.data || "Dispatcher registration failed. Please try again.";
          reponseMessage.style.color = "red";
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        const responseMessage = document.getElementById("riderMessage");
        responseMessage.textContent =
          "An error occurred during dispatcher registration.";
        responseMessage.style.color = "red";
      });
  });
}

if (bookRide) {
  bookRide.addEventListener("submit", function (event) {
    event.preventDefault();
    const user = JSON.parse(localStorage.getItem("flashUser"));
    const userId = user.data.id;

    const pickUpAddress = document.getElementById("pickup-address").value;
    const destinationAddress = document.getElementById(
      "destination-address"
    ).value;
    const price = document.getElementById("ride-price").value;

    fetch("http://localhost:8080/api/logistics/driver/login/ride-request", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userId: userId,
        destinationAddress: destinationAddress,
        pickupAddress: pickUpAddress,
        price: price,
      }),
    })
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        const reponseMessage = document.getElementById("rideMessage");
        if (response.ok) {
          alert("Driver found kindy confirm the Ride  activity");
          showSection("dashboard");
        } else {
          if (data.data === "No Driver available at the moment") {
            alert(data.data);
            showSection("dashboard");
          }
          reponseMessage.textContent =
            data.data || "Ride request failed. Please try again.";
          reponseMessage.style.color = "red";
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        const responseMessage = document.getElementById("rideMessage");
        responseMessage.textContent = "An error occurred during ride request.";
        responseMessage.style.color = "red";
      });
  });
}

if (bookDispatcher) {
  bookDispatcher.addEventListener("submit", function (event) {
    event.preventDefault();
    const user = JSON.parse(localStorage.getItem("flashUser"));
    const userId = user.data.id;

    const receiverPhoneNumber = document.getElementById("receiver-phone").value;
    const receiverName = document.getElementById("receiver-name").value;
    const senderPhoneNumber = document.getElementById("sender-phone").value;
    const pickUpAddress = document.getElementById(
      "pickup-address-dispatcher"
    ).value;
    const deliveryAddress = document.getElementById("delivery-address").value;
    const price = document.getElementById("dispatcher-price").value;

    fetch(" http://localhost:8080/api/logistics/rider/login/dispatch-request", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        receiverPhoneNumber: receiverPhoneNumber,
        receiverName: receiverName,
        senderPhoneNumber: senderPhoneNumber,
        pickUpAddress: pickUpAddress,
        deliveryAddress: deliveryAddress,
        userId: userId,
        price: price,
      }),
    })
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        const reponseMessage = document.getElementById("dispatcherMessage");
        if (response.ok) {
          alert("Dispatcher found kindly confirm the activity");
          showSection("dashboard");
        } else {
          if (data.data === "No dispatch rider available at the moment") {
            alert(data.data);
            showSection("dashboard");
          }
          reponseMessage.textContent =
            data.data || "Dispatcher request failed. Please try again.";
          reponseMessage.style.color = "red";
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        const responseMessage = document.getElementById("dispatcherMessage");
        responseMessage.textContent =
          "An error occurred during dispatcher request.";
        responseMessage.style.color = "red";
      });
  });
}

if (confirmAvailabilityYes) {
  confirmAvailabilityYes.addEventListener("click", function (event) {
    event.preventDefault();
    const user = JSON.parse(localStorage.getItem("flashUser"));
    const userType = user.data.userType?.toUpperCase();
    const userId = user.data.id;

    if (userType === "DRIVER") {
      fetch(
        "http://localhost:8080/api/logistics/driver/login/driver-available",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            driverId: userId,
          }),
        }
      )
        .then(async (response) => {
          const data = await response.json();
          console.log(data);
          const reponseMessage = document.getElementById("availabilityMessage");
          if (response.ok) {
            alert("Availability confirmed!");
            showSection("dashboard");
          } else {
            reponseMessage.textContent =
              data.data ||
              "Availability confirmation failed. Please try again.";
            reponseMessage.style.color = "red";
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          const responseMessage = document.getElementById(
            "availabilityMessage"
          );
          responseMessage.textContent =
            "An error occurred during availability confirmation.";
          responseMessage.style.color = "red";
        });
    }
    if (userType === "DISPATCHER") {
      fetch("http://localhost:8080/api/logistics/rider/login/rider-available", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          riderId: userId,
        }),
      })
        .then(async (response) => {
          const data = await response.json();
          console.log(data);
          const reponseMessage = document.getElementById("availabilityMessage");
          if (response.ok) {
            alert("Availability confirmed!");
            showSection("dashboard");
          } else {
            reponseMessage.textContent =
              data.data ||
              "Availability confirmation failed. Please try again.";
            reponseMessage.style.color = "red";
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          const responseMessage = document.getElementById(
            "availabilityMessage"
          );
        });
    }
  });
}

if (confirmAvailabilityNo) {
  confirmAvailabilityNo.addEventListener("click", function (event) {
    event.preventDefault();
    alert("Availability not confirmed!");
    showSection("dashboard");
  });
}

function checkNotification() {
  const user = JSON.parse(localStorage.getItem("flashUser"));
  if (
    !user ||
    !["DRIVER", "DISPATCHER"].includes(user.data.userType.toUpperCase())
  ) {
    alert("Only drivers and dispatchers can check notifications.");
    showSection("dashboard");
    return;
  }
  if (user?.data?.notification?.message) {
    const notification = user.data.notification;
    let message;
    if (user.data.userType.toUpperCase() === "DRIVER") {
      message =
        `📢 New Ride Assigned:\n` +
        `Ride ID: ${notification.rideId}\n` +
        `Pickup: ${notification.pickupAddress}\n` +
        `Destination: ${notification.destinationAddress}\n` +
        `Price: $${notification.price}\n` +
        `Message: ${notification.message}`;
    } else {
      message =
        `📢 Ride Booked:\n` +
        `Ride ID: ${notification.rideId}\n` +
        `Pickup: ${notification.pickupAddress}\n` +
        `Destination: ${notification.destinationAddress}\n` +
        `Price: $${notification.price}\n` +
        `Message: ${notification.message}`;
    }
    alert(message);
    fetch("http://localhost:8080/api/logistics/user/clear-notification", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ userId: user.data.id }),
    })
      .then(async (response) => {
        const data = await response.json();
        console.log(data);
        if (!response.ok) {
          throw new Error("Error clearing notification");
        }
        user.data.notification = null;
        localStorage.setItem("flashUser", JSON.stringify(user));
        showSection("dashboard");
      })
      .catch((error) => {
        console.error("Error clearing notification:", error);
        alert("Error processing notification.");
      });
  } else {
    alert("🔕 You have no new notifications.");
    showSection("dashboard");
  }
}

function updateNotificationButton() {
  const user = JSON.parse(localStorage.getItem("flashUser"));
  const notificationBtn = document.getElementById("notification-btn");
  if (user?.data?.notification?.message) {
    notificationBtn.innerHTML = `🔔 Notifications <span class="badge">New</span>`;
    notificationBtn.classList.add("has-notification");
  } else {
    notificationBtn.innerHTML = `🔔 Notifications`;
    notificationBtn.classList.remove("has-notification");
  }
}
