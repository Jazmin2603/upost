    let currentUser = null;
    let users = [];
    let posts = [];
    let comments = [];
    let likes = [];

    function initApp() {
    const savedData = {
    users: JSON.parse(localStorage.getItem('upost_users') || '[]'),
    posts: JSON.parse(localStorage.getItem('upost_posts') || '[]'),
    comments: JSON.parse(localStorage.getItem('upost_comments') || '[]'),
    likes: JSON.parse(localStorage.getItem('upost_likes') || '[]')
};

    users = savedData.users;
    posts = savedData.posts;
    comments = savedData.comments;
    likes = savedData.likes;

    if (users.length === 0) {
    users = [
{ codigo: 1, nombre: 'Juan Pérez', email: 'juan@email.com', password: '123456' },
{ codigo: 2, nombre: 'María García', email: 'maria@email.com', password: '123456' }
    ];
    saveData();
}
}

    function saveData() {
    localStorage.setItem('upost_users', JSON.stringify(users));
    localStorage.setItem('upost_posts', JSON.stringify(posts));
    localStorage.setItem('upost_comments', JSON.stringify(comments));
    localStorage.setItem('upost_likes', JSON.stringify(likes));
}

    function toggleAuthMode() {
    document.getElementById('authSection').classList.toggle('hidden');
    document.getElementById('registerSection').classList.toggle('hidden');
    document.getElementById('loginError').classList.add('hidden');
    document.getElementById('registerError').classList.add('hidden');
}

    function login() {
    const email = document.getElementById('loginEmail').value.trim();
    const password = document.getElementById('loginPassword').value;
    const errorDiv = document.getElementById('loginError');

    if (!email || !password) {
    errorDiv.textContent = 'Por favor completa todos los campos';
    errorDiv.classList.remove('hidden');
    return;
}

    const user = users.find(u => u.email === email && u.password === password);

    if (user) {
    currentUser = user;
    document.getElementById('authSection').classList.add('hidden');
    document.getElementById('appSection').classList.remove('hidden');
    document.getElementById('currentUser').textContent = user.nombre;
    renderPosts();
} else {
    errorDiv.textContent = 'Email o contraseña incorrectos';
    errorDiv.classList.remove('hidden');
}
}

    function register() {
    const name = document.getElementById('registerName').value.trim();
    const email = document.getElementById('registerEmail').value.trim();
    const password = document.getElementById('registerPassword').value;
    const errorDiv = document.getElementById('registerError');

    if (!name || !email || !password) {
    errorDiv.textContent = 'Por favor completa todos los campos';
    errorDiv.classList.remove('hidden');
    return;
}

    if (users.find(u => u.email === email)) {
    errorDiv.textContent = 'Este email ya está registrado';
    errorDiv.classList.remove('hidden');
    return;
}

    const newUser = {
    codigo: users.length > 0 ? Math.max(...users.map(u => u.codigo)) + 1 : 1,
    nombre: name,
    email: email,
    password: password
};

    users.push(newUser);
    saveData();

    errorDiv.textContent = 'Registro exitoso. Inicia sesión para continuar.';
    errorDiv.style.color = 'var(--color-500)';
    errorDiv.classList.remove('hidden');

    setTimeout(() => {
    toggleAuthMode();
    document.getElementById('registerName').value = '';
    document.getElementById('registerEmail').value = '';
    document.getElementById('registerPassword').value = '';
    errorDiv.classList.add('hidden');
    errorDiv.style.color = '#dc2626';
}, 2000);
}

    function logout() {
    currentUser = null;
    document.getElementById('appSection').classList.add('hidden');
    document.getElementById('authSection').classList.remove('hidden');
    document.getElementById('loginEmail').value = '';
    document.getElementById('loginPassword').value = '';
}

    function createPost() {
    const content = document.getElementById('newPostContent').value.trim();

    if (!content) {
    alert('Por favor escribe algo antes de publicar');
    return;
}

    const newPost = {
    id: posts.length > 0 ? Math.max(...posts.map(p => p.id)) + 1 : 1,
    mensaje: content,
    id_usuario: currentUser.codigo,
    timestamp: new Date().toISOString()
};

    posts.unshift(newPost);
    saveData();
    document.getElementById('newPostContent').value = '';
    renderPosts();
}

    function toggleLike(postId) {
    const existingLike = likes.find(l => l.id_post === postId && l.id_usuario === currentUser.codigo);

    if (existingLike) {
    likes = likes.filter(l => !(l.id_post === postId && l.id_usuario === currentUser.codigo));
} else {
    likes.push({
    id_post: postId,
    id_usuario: currentUser.codigo
});
}

    saveData();
    renderPosts();
}

    function addComment(postId) {
    const input = document.getElementById(`comment-input-${postId}`);
    const content = input.value.trim();

    if (!content) return;

    const newComment = {
    id: comments.length > 0 ? Math.max(...comments.map(c => c.id)) + 1 : 1,
    mensaje: content,
    id_post: postId,
    id_usuario: currentUser.codigo,
    timestamp: new Date().toISOString()
};

    comments.push(newComment);
    saveData();
    input.value = '';
    renderPosts();
}

    function formatTime(timestamp) {
    const date = new Date(timestamp);
    const now = new Date();
    const diff = now - date;
    const minutes = Math.floor(diff / 60000);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);

    if (minutes < 1) return 'Ahora';
    if (minutes < 60) return `Hace ${minutes}m`;
    if (hours < 24) return `Hace ${hours}h`;
    return `Hace ${days}d`;
}

    function renderPosts() {
    const container = document.getElementById('postsContainer');
    container.innerHTML = '';

    posts.forEach(post => {
    const author = users.find(u => u.codigo === post.id_usuario);
    const postLikes = likes.filter(l => l.id_post === post.id);
    const postComments = comments.filter(c => c.id_post === post.id);
    const isLiked = postLikes.some(l => l.id_usuario === currentUser.codigo);

    const postElement = document.createElement('div');
    postElement.className = 'post';
    postElement.innerHTML = `
                    <div class="post-header">
                        <span class="post-author">${author.nombre}</span>
                        <span class="post-time">${formatTime(post.timestamp)}</span>
                    </div>
                    <div class="post-content">${post.mensaje}</div>
                    <div class="post-actions">
                        <button class="action-btn ${isLiked ? 'liked' : ''}" onclick="toggleLike(${post.id})">
                            ❤ ${postLikes.length}
                        </button>
                        <button class="action-btn">
                            💬 ${postComments.length}
                        </button>
                    </div>
                    <div class="comments-section">
                        ${postComments.map(comment => {
    const commentAuthor = users.find(u => u.codigo === comment.id_usuario);
    return `
                                <div class="comment">
                                    <div class="comment-header">${commentAuthor.nombre}</div>
                                    <div class="comment-content">${comment.mensaje}</div>
                                </div>
                            `;
}).join('')}
                        <div class="comment-form">
                            <input type="text" id="comment-input-${post.id}" placeholder="Escribe un comentario...">
                            <button class="btn btn-primary btn-small" onclick="addComment(${post.id})">Comentar</button>
                        </div>
                    </div>
                `;
    container.appendChild(postElement);
});
}

    initApp();