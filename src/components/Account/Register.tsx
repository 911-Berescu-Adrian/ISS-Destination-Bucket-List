import { useState } from "react";
import { Link } from "react-router-dom";


export const Register = () => {

  const [email, setEmail] = useState('');
  const [passwd, setPasswd] = useState('');


  const validateRegister = async () => {
    
    

    console.log()
  };


  return (
    <>
        <form className="flex flex-col" onSubmit={validateRegister}>
            <div>
              <input 
                id="email" 
                type="text" 
                placeholder='Email'
                value={email}
                onChange={(e) => setEmail(e.target.value)}  />
            </div>
            <div>
              <input 
                id="password" 
                type="password" 
                placeholder='Password'
                value={passwd}
                onChange={(e) => setPasswd(e.target.value)} />
            </div>
            <div><button type="submit" className="text-2xl">Register</button></div>
        </form>
        <Link to="/">
            Back
        </Link>
        
    </>
  )
}

