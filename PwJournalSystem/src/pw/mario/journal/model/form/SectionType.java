package pw.mario.journal.model.form;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.Getter;
import pw.mario.common.api.ComponentBuilder;
import pw.mario.common.api.Filler;
import pw.mario.journal.view.form.section.MultiCheckboxSection;
import pw.mario.journal.view.form.section.SingleBoxSection;
import pw.mario.journal.view.form.section.TextSection;

public enum SectionType {
	CHECKBOX("Checkboxy", new MultiFiller()),
	SINGLEBOX("Single selections", new SingleFiller()),
	TEXT("Text", new NoneFiller());
	
	public static final List<SectionType> list;
	
	static {
		List<SectionType> types = new LinkedList<>();
		Collections.addAll(types, SectionType.values());
		list = Collections.unmodifiableList(types);
	}
	
	@Getter private String name;
	private Filler<Section> filler;
	
	
	private SectionType(String name, Filler filler) {
		this.name = name;
		this.filler = filler;
	}
	
	public ComponentBuilder builder(Section s, String path) {
		switch(this) {
			case CHECKBOX:
				return new MultiCheckboxSection(s, path);
			case SINGLEBOX:
				return new SingleBoxSection(s, path);
			case TEXT:
				return new TextSection(s, path);
			default:
				throw new RuntimeException(this.getName() + " not implemented");
		}
	}
	
	public void fill(Section s) {
		filler.fill(s);
	}
	
	public void propagate(Section s) {
		filler.propagate(s);
	}
	
	private static class SingleFiller implements Filler<Section> {

		@Override
		public void fill(Section toFill) {
			try {
				toFill.setSelectedElementId(
						toFill.getElements()
							.stream()
							.filter(el -> SingleBoxSection.SELECTED_VALUE.equals(el.getValue()))
							.findFirst()
							.get()
							.getElemId()
							.toString()
				);
			} catch (NoSuchElementException e) {}
		}

		@Override
		public void propagate(Section toPropagate) {
			if (toPropagate.getSelectedElementId() != null)
				toPropagate.getElements().forEach(el -> el.setValue(null));
			else { 
				Long elemId = Long.parseLong(toPropagate.getSelectedElementId());
				toPropagate.getElements().forEach(el -> {
					if (elemId.compareTo(el.getElemId()) == 0)
						el.setValue(SingleBoxSection.SELECTED_VALUE);
					else
						el.setValue(null);
				});
			}
		}
	}
	
	private static class MultiFiller implements Filler<Section> {

		@Override
		public void fill(Section toFill) {
					toFill.getElements()
						.stream()
						.filter(el -> MultiCheckboxSection.SELECTED_VALUE.equals(el.getValue()))
						.collect(Collectors.toList())
						.forEach(el -> toFill.getSelectedElementsIds().add(el.getElemId().toString()));
		}

		@Override
		public void propagate(Section toPropagate) {
			if (toPropagate.getElements() == null)
				return;
			if (toPropagate.getSelectedElementsIds() == null || toPropagate.getSelectedElementsIds().size() == 0)
				toPropagate.getElements().forEach(el -> el.setValue(null));
			else { 
				toPropagate.getElements().forEach(el -> {
					if (toPropagate.getSelectedElementsIds().contains(el.getElemId().toString()))
						el.setValue(MultiCheckboxSection.SELECTED_VALUE);
					else
						el.setValue(null);
				});
			}
			
		}
	}
	
	private static class NoneFiller implements Filler<Section> {

		@Override
		public void fill(Section toFill) {
		}

		@Override
		public void propagate(Section toPropagate) {
		}
	}
}
