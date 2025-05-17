svnadmin create repo
REPO_URL="file://$(pwd)/repo"


# Создаём базовую структуру: trunk (будем считать его аналогом branch1) и branches
svn mkdir -m "Создание структуры проекта" "$REPO_URL/trunk" "$REPO_URL/branches"

# Чекаут рабочей копии из trunk (branch1)
svn checkout "$REPO_URL/trunk" wc
