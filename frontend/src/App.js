import './App.css';
import { MDBCol, MDBContainer, MDBRow, MDBInput } from "mdb-react-ui-kit";
import axios from "axios";
import { useState, useEffect } from "react";

function App() {
  const [genres, setGenres] = useState([]);
  const [movies, setMovies] = useState([]);
  const [activeGenre, setActiveGenre] = useState(0);
  
  useEffect(() => {
    axios
      .get("/movies/v1/genres/list")
      .then((res) => {
        console.log("genres", res);
        setGenres(res.data);
      })
      .catch((res) => {
        console.log(res);
      });
  }, []);

  useEffect(() => {
    axios
      .get("/movies/v1/movies/list")
      .then((res) => {
        console.log("movies", res);
        setMovies(res.data);
      })
      .catch((res) => {
        console.log(res);
      });
  }, []);

  let genreCells =  genres.map(
      (genre) => (<Genre key= {genre.id} id={genre.id} name={genre.name} setActiveGenre={setActiveGenre} />)
    )

  let movieList = Object.values(movies).flat()

  let filteredMovies = movieList.filter((movie)=> {
    return movie.genreIds.includes(activeGenre)
  })

  let movieCells =  filteredMovies.map(
    (movie) => (<Movie key= {movie.name} genreIds={movie.genreIds} title={movie.title} image={movie.posterPath} overview={movie.overview}/>)
  )

  return (
    <div className="row vh-100 bg-dark p-3 py-4">
      <div className="col-4 flex-wrap ">
        {genreCells}
      </div>
      <div className="col-8 d-flex align-items-start flex-wrap ">
        {movieCells}
      </div>
    </div>
  );
}

function Genre(props){
  return(
      <a
          data-mdb-pill-init
          className="btn bg-white btn-rounded m-1 "
          id="{props.id}"
          onClick={()=> props.setActiveGenre(props.id)}
          >{props.name}</a
        >
    )
}

function Movie(props){
  return(
    <div className="card w-25 m-2 mh-10">
      <div className=" mx-auto" data-mdb-ripple-init data-mdb-ripple-color="light">
        <img src={"https://image.tmdb.org/t/p/w220_and_h330_face/" + props.image}/>
      </div>
      <div className="card-body">
        <h5 className="card-title">{props.title}</h5>
      </div>
    </div>
    )
}

export default App;
