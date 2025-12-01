document.addEventListener('DOMContentLoaded', function() {
    // Login form validation
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const errorElement = document.getElementById('passwordError');

            if (password.length > 0 && password.length < 8) {
                e.preventDefault();
                errorElement.textContent = 'Password must be at least 8 characters';
                errorElement.style.display = 'block';
            } else {
                errorElement.style.display = 'none';
            }
        });
    // ... existing login validation code ...

        // Registration form validation
        const registerForm = document.getElementById('registerForm');
        if (registerForm) {
            // Add role validation
            registerForm.addEventListener('submit', function(e) {
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirmPassword').value;
                const role = document.getElementById('role').value;
                const passwordError = document.getElementById('passwordError');
                const confirmError = document.getElementById('confirmError');
                let isValid = true;

                // Validate role selection
                if (!role) {
                    const roleSelect = document.getElementById('role');
                    roleSelect.style.borderColor = '#dc3545';
                    isValid = false;

                    // Create error message if it doesn't exist
                    let roleError = document.getElementById('roleError');
                    if (!roleError) {
                        roleError = document.createElement('div');
                        roleError.id = 'roleError';
                        roleError.className = 'error-message';
                        roleError.textContent = 'Please select a role';
                        roleSelect.parentNode.appendChild(roleError);
                    }
                    roleError.style.display = 'block';
                } else {
                    const roleSelect = document.getElementById('role');
                    roleSelect.style.borderColor = '#e0e0e0';
                    const roleError = document.getElementById('roleError');
                    if (roleError) roleError.style.display = 'none';
                }

                // Validate password length
                if (password.length < 8) {
                    passwordError.textContent = 'Password must be at least 8 characters';
                    passwordError.style.display = 'block';
                    isValid = false;
                } else {
                    passwordError.style.display = 'none';
                }

                // Validate password match
                if (password !== confirmPassword) {
                    confirmError.textContent = 'Passwords do not match';
                    confirmError.style.display = 'block';
                    isValid = false;
                } else {
                    confirmError.style.display = 'none';
                }

                if (!isValid) {
                    e.preventDefault();
                }
            });

            // Real-time role validation
            const roleSelect = document.getElementById('role');
            if (roleSelect) {
                roleSelect.addEventListener('change', function() {
                    if (this.value) {
                        this.style.borderColor = '#e0e0e0';
                        const roleError = document.getElementById('roleError');
                        if (roleError) roleError.style.display = 'none';
                    }
                });
            }
        }
    }

    // Registration form validation
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const passwordError = document.getElementById('passwordError');
            const confirmError = document.getElementById('confirmError');
            let isValid = true;

            // Validate password length
            if (password.length < 8) {
                passwordError.textContent = 'Password must be at least 8 characters';
                passwordError.style.display = 'block';
                isValid = false;
            } else {
                passwordError.style.display = 'none';
            }

            // Validate password match
            if (password !== confirmPassword) {
                confirmError.textContent = 'Passwords do not match';
                confirmError.style.display = 'block';
                isValid = false;
            } else {
                confirmError.style.display = 'none';
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    }

    // Remember me functionality
    const rememberCheckbox = document.getElementById('remember');
    if (rememberCheckbox) {
        // Check if we have saved credentials
        const savedEmail = localStorage.getItem('rememberedEmail');
        const savedPassword = localStorage.getItem('rememberedPassword');

        if (savedEmail && savedPassword) {
            document.getElementById('email').value = savedEmail;
            document.getElementById('password').value = savedPassword;
            rememberCheckbox.checked = true;
        }

        // Save credentials when checkbox is checked
        rememberCheckbox.addEventListener('change', function() {
            if (this.checked) {
                const email = document.getElementById('email').value;
                const password = document.getElementById('password').value;

                if (email && password.length >= 8) {
                    localStorage.setItem('rememberedEmail', email);
                    localStorage.setItem('rememberedPassword', password);
                }
            } else {
                localStorage.removeItem('rememberedEmail');
                localStorage.removeItem('rememberedPassword');
            }
        });
    }

    // Real-time password validation
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
        passwordInput.addEventListener('input', function() {
            const errorElement = document.getElementById('passwordError');
            if (this.value.length > 0 && this.value.length < 8) {
                errorElement.textContent = 'Password must be at least 8 characters';
                errorElement.style.display = 'block';
            } else {
                errorElement.style.display = 'none';
            }
        });
    }

    // Real-time password confirmation validation
    const confirmInput = document.getElementById('confirmPassword');
    if (confirmInput) {
        confirmInput.addEventListener('input', function() {
            const password = document.getElementById('password').value;
            const errorElement = document.getElementById('confirmError');

            if (password !== this.value) {
                errorElement.textContent = 'Passwords do not match';
                errorElement.style.display = 'block';
            } else {
                errorElement.style.display = 'none';
            }
        });
    }
});