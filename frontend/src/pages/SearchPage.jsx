import { useState } from 'react'
import MovieCard from '../components/MovieCard'
import './SearchPage.css'


function SearchPage() {
  const [searchTerm, setSearchTerm] = useState('')
  const [movies, setMovies] = useState([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const searchMovies = async () => {
    if (!searchTerm.trim()) {
      setError('Please enter a movie name.')
      setMovies([])
      return
    }

    setLoading(true)
    setError('')

    try {
      const response = await fetch(//Inside this async function,await means pause this function here until the result arrives, then continue.
        `https://www.omdbapi.com/?apikey=${import.meta.env.VITE_OMDB_API_KEY}&s=${searchTerm}`//means search
      )

      if (!response.ok) {
        throw new Error('Failed to fetch movies.')
      }

      const data = await response.json()//JSON-formatted data into a JavaScript object.

      if (data.Response === 'False') {
        setMovies([])
        setError('No movies found.')
        return
      }

      setMovies(data.Search || [])
      //gives only the result list from the response object,
      // rest of key-values(Search, totalResults, Response) are ignored
    } catch (error) {
      setMovies([])
      setError('Something went wrong. Please try again.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className='movie-page'>
      <h1>🎬 Movie Explorer</h1>

      <input
        type="text"
        placeholder="Search for a movie"
        value={searchTerm}//js written in {curly braces} in HTML ~ JSP
        onChange={(event) => setSearchTerm(event.target.value)}
      // event is the entire event object generated when i type/change the input
      />

      <button onClick={searchMovies}>
        Search
      </button>
      {loading && <p>Searching...</p>}

      {error && <p>{error}</p>}
      <br />
      <br />
      <br />

      <div className="movie-grid">
        {movies.map((movie) => (
          <MovieCard key={movie.imdbID} movie={movie} />
        ))}
      </div>
      {/* app must first get the updated react data; key then helps React handle the changed list. */}
      <p>You are searching for: {searchTerm}</p>
    </div>
  )
}


export default SearchPage