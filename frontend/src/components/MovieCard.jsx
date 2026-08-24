import { Link } from 'react-router-dom'
import './MovieCard.css'
function MovieCard({ movie }) {
  return (
    <div style={{ border: "2px solid green" }} className='movie-card'>
      <Link style={{ textDecoration: "none" }} to={`/movie/${movie.imdbID}`}>
        <h2>{movie.Title}</h2>
        <p>{movie.Year}</p>
        <img
          src={movie.Poster}
          alt={movie.Title}
          width="150"
        />
      </Link>
    </div>
  )
}

export default MovieCard