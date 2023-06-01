import { Link } from "react-router-dom"


export const LandingPage = () => {

    


  return (
    <>
        <h1 className="text-3xl">Plan your dream vacation.</h1>
        <div className="">
            <Link to="/login">
                <button>
                    Login
                </button>
            </Link>
            <Link to="/register">
                <button>
                    Register
                </button>
            </Link>
        </div>
    </>
  )
}

