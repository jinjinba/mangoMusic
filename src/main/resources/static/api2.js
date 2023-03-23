let playlist = [];
let currentTrackIndex = 0;

let player;

window.onYouTubeIframeAPIReady = function () {
    window.onYouTubeIframeAPIReady = onYouTubeIframeAPIReady;
    player = new YT.Player("player", {
        height: "0",
        width: "0",
        events: {
            onReady: onPlayerReady,
            onStateChange: onPlayerStateChange
        }
    });
};


function onPlayerReady(event) {
    // 플레이어 준비가 되면, 첫 번째 트랙을 재생할 수 있도록 합니다.
    loadVideoById(playlist[currentTrackIndex]);
}

function onPlayerStateChange(event) {
    // 곡이 종료되면 다음 곡을 자동으로 재생합니다.
    if (event.data === YT.PlayerState.ENDED) {
        playNextSong();
    }
}

function playTrack(videoId, trackName, artistName, imageUrl) {
    playlist.push(videoId);
    currentTrackIndex = playlist.length - 1;

    // 곡 정보를 플레이 바에 표시합니다.
    document.querySelector("#song-title").textContent = trackName;
    document.querySelector("#song-artist").textContent = artistName;
    document.querySelector("#album-art").src = imageUrl;

    loadVideoById(videoId);

    console.log("Playing videoId:", videoId);
}

function loadVideoById(videoId) {
    if (player && typeof player.loadVideoById === "function") {
        player.loadVideoById({ videoId: videoId, endSeconds: null });
        player.playVideo();
    }
}


function togglePlayPause() {
    if (player && typeof player.getPlayerState === "function") {
        if (player.getPlayerState() === YT.PlayerState.PLAYING) {
            player.pauseVideo();
            document.querySelector("#play-pause-btn-new").textContent = "Play";
        } else {
            player.playVideo();
            document.querySelector("#play-pause-btn-new").textContent = "Pause";
        }
    }
}


function playPreviousSong() {
    if (currentTrackIndex > 0) {
        currentTrackIndex--;
        loadVideoById(playlist[currentTrackIndex]);
    }
}

function playNextSong() {
    if (currentTrackIndex < playlist.length - 1) {
        currentTrackIndex++;
        loadVideoById(playlist[currentTrackIndex]);
    }
}

function getTopTracks() {
    const apiKey = "5aab513055fea4a2802fbd695ea148a9";
    const limit = 10;
    const url = `https://ws.audioscrobbler.com/2.0/?method=chart.getTopTracks&api_key=${apiKey}&format=json&limit=${limit}`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const topTracks = data.tracks.track;

            const topCards = document.querySelector("#topsidecards");
            topCards.innerHTML = ""; // 이전 목록 삭제

            topTracks.forEach(track => {
                const col = document.createElement("div");
                col.classList.add("col");

                const card = document.createElement("div");
                card.classList.add("background-card", "mb-3");
                card.style.maxWidth = "540px";

                const row = document.createElement("div");
                row.classList.add("row", "g-0");

                const imgCol = document.createElement("div");
                imgCol.classList.add("col-md-4");

                const imgLink = document.createElement("a");
                imgLink.setAttribute("href", "#");

                // YouTube API 키
                const YOUTUBE_API_KEY = "AIzaSyCnA74oy9ZZo3D0QBq02Ap8pE9Bwr3vEMo";

                imgLink.addEventListener("click", (event) => {
                    event.preventDefault();
                    const videoId = imgLink.getAttribute("data-video-id");
                    if (videoId) {
                        playTrack(videoId, track.name, track.artist.name, img.src);
                    } else {
                        // 곡명을 사용하여 YouTube 검색 진행
                        if (track.artist && track.artist.name && track.name) {
                            const searchTerm = `${track.artist.name} - ${track.name}`;
                            fetch(`https://www.googleapis.com/youtube/v3/search?key=${YOUTUBE_API_KEY}&part=snippet&type=video&q=${encodeURIComponent(searchTerm)}`)
                                .then(response => response.json())
                                .then(data => {
                                    if (data.items && data.items[0] && data.items[0].id && data.items[0].id.videoId) {
                                        const videoId = data.items[0].id.videoId;
                                        imgLink.setAttribute("data-video-id", videoId);
                                        playTrack(videoId, track.name, track.artist.name, img.src);
                                    }
                                });
                        }
                    }
                });


                const img = document.createElement("img");
                img.classList.add("img-fluid", "rounded-start");
                img.style
                img.style.height = "100%";
                img.style.minWidth = "100%";
                img.src = track.image[2]["#text"] || "https://via.placeholder.com/400x400.png?text=No+Image";

                imgLink.appendChild(img);
                imgCol.appendChild(imgLink);
                row.appendChild(imgCol);

                const bodyCol = document.createElement("div");
                bodyCol.classList.add("col-md-8", "d-flex", "align-items-center");

                const body = document.createElement("div");
                body.classList.add("card-body");

                const title = document.createElement("h5");
                title.classList.add("card-title");
                title.textContent = `${track.name} - ${track.artist.name}`;

                body.appendChild(title);
                bodyCol.appendChild(body);
                row.appendChild(bodyCol);
                card.appendChild(row);
                col.appendChild(card);
                topCards.appendChild(col);

                // 아티스트명과 앨범명을 가져와서 앨범 커버 이미지를 표시
                fetch(`https://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=${apiKey}&artist=${encodeURIComponent(track.artist.name)}&track=${encodeURIComponent(track.name)}&format=json`)
                    .then(response => response.json())
                    .then(data => {
                        if (data.track && data.track.artist && data.track.album && data.track.album.title) {
                            // track 정보를 이용해 artist.getTopAlbums API 호출
                            fetch(`https://ws.audioscrobbler.com/2.0/?method=artist.getTopAlbums&api_key=${apiKey}&artist=${encodeURIComponent(track.artist.name)}&format=json&limit=8`)
                                .then(response => response.json())
                                .then(data => {
                                    if (data.topalbums && data.topalbums.album && data.topalbums.album[0] && data.topalbums.album[0].image && data.topalbums.album[0].image[2]) {
                                        img.src = data.topalbums.album[0].image[2]["#text"];
                                    }
                                });
                        }
                    });
            });
        });
}

