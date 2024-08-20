export function SignUp() {
    return (
        <> 
        <h1>Sign Up</h1>
        <div>
            <label htmlFor="username">Username</label>
            <input id="username" type="username" />
        </div>
        <div>
            <label htmlFor="email">Email</label>
            <input id="email" type="email" />
        </div>
        <div>
            <label htmlFor="password">Password</label>
            <input id="password" type="password" />
        </div>
        <div>
            <label htmlFor="passwordRepeat">Repeat Password</label>
            <input id="passwordRepeat" type="password" />
        </div>
        <button>Sign Up</button>

      </>
     
    )
}