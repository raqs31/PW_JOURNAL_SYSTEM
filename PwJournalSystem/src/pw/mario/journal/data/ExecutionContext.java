package pw.mario.journal.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pw.mario.journal.model.Article;
import pw.mario.journal.model.Rule;
import pw.mario.journal.model.User;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(doNotUseGetters=true)
@Builder
public class ExecutionContext {
	@Getter private Article article;
	@Getter private Rule rule;
	@Getter private User user;
	@Getter @Setter private User manager;
	@Getter @Setter private List<User> acceptors;

}