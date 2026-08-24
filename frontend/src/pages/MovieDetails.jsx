import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import './MovieDetails.css'

function MovieDetails() {
    const { id: movieId } = useParams()

    const [movie, setMovie] = useState(null)
    const [reviewText, setReviewText] = useState('')
    const [rating, setRating] = useState(1)
    const [savedReviews, setSavedReviews] = useState([])

    const [editingReviewId, setEditingReviewId] = useState(null)
    const [editText, setEditText] = useState('')
    const [editRating, setEditRating] = useState(1)

    const submitReview = async () => {
        if (reviewText.trim().length < 3) {
            alert('Review must contain at least 3 characters.')
            return
        }

        if (reviewText.trim().length > 500) {
            alert('Review cannot exceed 500 characters.')
            return
        }

        const response = await fetch(
            'http://localhost:8080/reviews/save',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    movie_id: movieId,
                    text: reviewText,
                    rating: Number(rating)
                })
            }
        )

        if (!response.ok) {
            alert('Failed to submit review.')
            return
        }

        const reviewResponse = await fetch(
            `http://localhost:8080/reviews/movie/${movieId}`
        )

        const updatedReviews = await reviewResponse.json()

        setSavedReviews(updatedReviews)
        setReviewText('')
        setRating(1)
    }

    const deleteReview = async (reviewId) => {
        const response = await fetch(
            `http://localhost:8080/reviews/delete/review/${reviewId}`,
            {
                method: 'DELETE'
            }
        )

        if (!response.ok) {
            alert('Failed to delete review.')
            return
        }

        const reviewResponse = await fetch(
            `http://localhost:8080/reviews/movie/${movieId}`
        )

        const updatedReviews = await reviewResponse.json()

        setSavedReviews(updatedReviews)
    }

    const updateReview = async (reviewId) => {
        if (editText.trim().length < 3) {
            alert('Review must contain at least 3 characters.')
            return
        }

        if (editText.trim().length > 500) {
            alert('Review cannot exceed 500 characters.')
            return
        }

        const response = await fetch(
            `http://localhost:8080/reviews/update/review/${reviewId}`,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    movie_id: movieId,
                    text: editText,
                    rating: Number(editRating)
                })
            }
        )

        if (!response.ok) {
            alert('Failed to update review.')
            return
        }

        const reviewResponse = await fetch(
            `http://localhost:8080/reviews/movie/${movieId}`
        )

        const updatedReviews = await reviewResponse.json()

        setSavedReviews(updatedReviews)
        setEditingReviewId(null)
        setEditText('')
        setEditRating(1)
    }

    useEffect(() => {
        const getReviews = async () => {
            const response = await fetch(
                `http://localhost:8080/reviews/movie/${movieId}`
            )

            if (!response.ok) {
                return
            }

            const data = await response.json()

            setSavedReviews(data)
        }

        getReviews()
    }, [movieId])

    useEffect(() => {
        const getMovieDetails = async () => {
            const response = await fetch(
                `https://www.omdbapi.com/?apikey=${import.meta.env.VITE_OMDB_API_KEY}&i=${movieId}&plot=full`
            )

            const data = await response.json()

            setMovie(data)
        }

        getMovieDetails()
    }, [movieId])

    if (!movie) {
        return <p>Loading movie details...</p>
    }

    return (
        <div className="movie-details">

            <h1>{movie.Title}</h1>

            <img
                src={movie.Poster}
                alt={movie.Title}
            />

            <p>
                <strong>Year:</strong> {movie.Year}
            </p>

            <p>
                <strong>Director:</strong> {movie.Director}
            </p>

            <p>
                <strong>Actors:</strong> {movie.Actors}
            </p>

            <p>
                <strong>IMDb Rating:</strong> {movie.imdbRating}
            </p>

            <p>
                <strong>Plot:</strong> {movie.Plot}
            </p>

            <h2>Write a Review</h2>

            <label>Rating: </label>

            <select
                value={rating}
                onChange={(event) =>
                    setRating(Number(event.target.value))
                }
            >
                <option value="1">⭐</option>
                <option value="2">⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="5">⭐⭐⭐⭐⭐</option>
            </select>

            <textarea
                placeholder="Write your review..."
                value={reviewText}
                onChange={(event) =>
                    setReviewText(event.target.value)
                }
            />

            <button onClick={submitReview}>
                Submit Review
            </button>

            <hr />

            <h2>Reviews</h2>

            <div className="review-grid">

                {savedReviews.map((review) => (

                    <div
                        className="review-grid-box"
                        key={review.id}
                    >

                        {editingReviewId === review.id ? (

                            <>
                                <select
                                    value={editRating}
                                    onChange={(event) =>
                                        setEditRating(
                                            Number(event.target.value)
                                        )
                                    }
                                >
                                    <option value="1">⭐</option>
                                    <option value="2">⭐⭐</option>
                                    <option value="3">⭐⭐⭐</option>
                                    <option value="4">⭐⭐⭐⭐</option>
                                    <option value="5">⭐⭐⭐⭐⭐</option>
                                </select>

                                <textarea
                                    value={editText}
                                    onChange={(event) =>
                                        setEditText(event.target.value)
                                    }
                                />

                                <div className="review-grid-box-buttons">

                                    <button
                                        onClick={() =>
                                            updateReview(review.id)
                                        }
                                    >
                                        Save Changes
                                    </button>

                                    <button
                                        onClick={() =>
                                            setEditingReviewId(null)
                                        }
                                    >
                                        Cancel
                                    </button>

                                </div>
                            </>

                        ) : (

                            <>
                                <p className="stars">
                                    {'⭐'.repeat(review.rating)}
                                </p>

                                <h3>
                                    Sentiment: {review.sentiment}
                                </h3>

                                <p className="review-text">
                                    {review.text}
                                </p>

                                <div className="review-grid-box-buttons">

                                    <button
                                        onClick={() => {
                                            setEditingReviewId(review.id)
                                            setEditText(review.text)
                                            setEditRating(review.rating)
                                        }}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        onClick={() =>
                                            deleteReview(review.id)
                                        }
                                    >
                                        Delete
                                    </button>

                                </div>
                            </>

                        )}

                    </div>

                ))}

            </div>

        </div>
    )
}

export default MovieDetails