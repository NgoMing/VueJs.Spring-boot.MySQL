export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'ngolenhatminh@gmail.com'
        ? resolve({ result: 'success' })
        : reject(new Error('User already existed'))
    })
  }
}
