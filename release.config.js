{
  "branches": ["master"],
  "plugins": [
    "@semantic-release/commit-analyzer",
    "@semantic-release/release-notes-generator",
    "@semantic-release/changelog",
    "@semantic-release/maven",   // Este plugin es específico para Maven
    "@semantic-release/github",
    "@semantic-release/git"
  ]
}
