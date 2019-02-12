export default {
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      (detail.username === 'ngoming' || detail.username === 'ngolenhatminh@gmail.com') &&
      detail.password === 'password'
        ? resolve({ result: 'success' })
        : reject(new Error('Invalid credentials'))
    })
  }
}
