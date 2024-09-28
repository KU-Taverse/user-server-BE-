package ku.user.domain.Ranking.domain;

public enum Status {
    ACTIVE("활성화"),
    DELETED("삭제됨"),
    INACTIVE("비활성화");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

